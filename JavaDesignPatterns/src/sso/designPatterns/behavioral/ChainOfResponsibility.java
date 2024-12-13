package sso.designPatterns.behavioral;

class LeaveRequest {
	private String employeeName;
	private int days;

	public LeaveRequest(String employeeName, int days) {
		this.employeeName = employeeName;
		this.days = days;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public int getDays() {
		return days;
	}
}

abstract class ApprovalHandler {
	protected ApprovalHandler nextHandler; // Sonraki işleyici

	public void setNextHandler(ApprovalHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract void handleRequest(LeaveRequest request); // İzin talebini işleme metodu
}

class ManagerHandler extends ApprovalHandler {

	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getDays() <= 2) {
			System.out.println("Manager approved " + request.getEmployeeName() + "'s leave request.");
		} else if (nextHandler != null) {
			nextHandler.handleRequest(request); // Bir sonraki işleyiciye ilet
		}
	}
}

class DirectorHandler extends ApprovalHandler {

	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getDays() <= 5) {
			System.out.println("Director approved " + request.getEmployeeName() + "'s leave request.");
		} else if (nextHandler != null) {
			nextHandler.handleRequest(request); // Bir sonraki işleyiciye ilet
		}
	}
}

class CEOHandler extends ApprovalHandler {

	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getDays() > 5) {
			System.out.println("CEO approved " + request.getEmployeeName() + "'s leave request.");
		}
	}
}

public class ChainOfResponsibility {
	public static void main(String[] args) {
		// İşleyiciler oluşturuluyor
		ApprovalHandler manager = new ManagerHandler();
		ApprovalHandler director = new DirectorHandler();
		ApprovalHandler ceo = new CEOHandler();

		// İşleyiciler zinciri kuruyoruz
		manager.setNextHandler(director);
		director.setNextHandler(ceo);

		// İzin talepleri oluşturuluyor
		LeaveRequest request1 = new LeaveRequest("John", 2); // 2 gün izin
		LeaveRequest request2 = new LeaveRequest("Alice", 4); // 4 gün izin
		LeaveRequest request3 = new LeaveRequest("Bob", 7); // 7 gün izin

		// Talepler işleniyor
		manager.handleRequest(request1);
		manager.handleRequest(request2);
		manager.handleRequest(request3);
	}
}
