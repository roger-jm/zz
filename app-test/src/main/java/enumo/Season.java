package enumo;

public enum Season {
	SPRING(1, "春天"), SUMMER(2, "夏天"), AUTUMN(3, "秋天"), WINTER(4, "冬天");

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
