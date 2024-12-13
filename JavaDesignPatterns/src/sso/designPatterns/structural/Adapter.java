package sso.designPatterns.structural;

interface JsonRequest {
    void sendJsonRequest(String jsonData);
}

class XmlRequest {
    public void sendXmlRequest(String xmlData) {
        System.out.println("Sending XML request: " + xmlData);
    }
}

class XmlToJsonAdapter implements JsonRequest {
    private XmlRequest xmlRequest;

    public XmlToJsonAdapter(XmlRequest xmlRequest) {
        this.xmlRequest = xmlRequest;
    }

    @Override
    public void sendJsonRequest(String jsonData) {
        String xmlData = convertJsonToXml(jsonData);  // JSON'dan XML'e dönüştürme
        xmlRequest.sendXmlRequest(xmlData);  // XML gönderme
    }

    private String convertJsonToXml(String jsonData) {
        // Bu örnekte basitçe dönüşüm yapılmakta
        return "<xml><data>" + jsonData + "</data></xml>";
    }
}

public class Adapter {
	public static void main(String[] args) {
        XmlRequest xmlRequest = new XmlRequest();  // XML formatında veri gönderen sistem
        JsonRequest jsonRequest = new XmlToJsonAdapter(xmlRequest);  // Adapter kullanarak uyumlulaştırıyoruz

        // JSON formatında veri gönderimi
        String jsonData = "{\"name\": \"John\", \"age\": 30}";
        jsonRequest.sendJsonRequest(jsonData);  // Adapter JSON'u alır, XML'e dönüştürüp gönderir
    }
}
