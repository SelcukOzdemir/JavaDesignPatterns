package sso.designPatterns.creational;

import java.util.UUID;

interface Coupon{
	String code();
	String message();
}

class FoodCoupon implements Coupon{

	@Override
	public String code() {
		
		return UUID.randomUUID().toString();
	}

	@Override
	public String message() {
		
		return "I am a food coupon";
	}
	
}

class ElectronicsCoupon implements Coupon{

	@Override
	public String code() {
		
		return UUID.randomUUID().toString();
	}

	@Override
	public String message() {
		
		return "I am a electronics coupon";
	}
	
}

class CouponFactory{
	public static Coupon getCoupon(int points) {
		if(points < 50){
			return new FoodCoupon();
		}
		return new ElectronicsCoupon();
	}
}

public class Factory {
	public static void main(String[] args) {
		Coupon coupon = CouponFactory.getCoupon(30);
		System.err.println(coupon.code() + " - " + coupon.message());
		
		Coupon coupon2 = CouponFactory.getCoupon(50);
		System.err.println(coupon2.code() + " - " + coupon2.message());
		
	}
}
