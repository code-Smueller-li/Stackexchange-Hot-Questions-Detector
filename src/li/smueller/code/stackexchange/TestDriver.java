package li.smueller.code.stackexchange;

public final class TestDriver {

	private TestDriver() {
		
	}
	
	public static final String URL = "travel.stackexchange.com";
	
	public static void main(String[] args) {
		(new HotQuestionDetector()).detect(URL);
	}
	
}