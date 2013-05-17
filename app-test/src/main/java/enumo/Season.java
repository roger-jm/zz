package enumo;

public enum Season {
	SPRING(1, "����"), SUMMER(2, "����"), AUTUMN(3, "����"), WINTER(4, "����");

	private int code;
	private String chineseName;

	public String getChineseName() {
		return chineseName;
	}

	private Season(int code, String chineseName) {
		this.code = code;
		this.chineseName = chineseName;
	}

	public int getCode() {
		return code;
	}
}
