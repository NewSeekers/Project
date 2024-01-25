package boardModel;

import java.sql.Timestamp;

public class BDto {
	private int rnum;
	private int bId;
	private String bTitle;
	private String bName;
	private String bContent;
	private String bDate;
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	private int bHit;
	private int bGroup;
	private int bStep;
	private int bIndent;
	
	public BDto () {
		
	}
	
	public BDto(int bId, String bName, String bTitle, String bContent, String bDate, int bHit, int bGroup, int bStep, int bIndent) {
		this.bId=bId;
		this.bTitle=bTitle;
		this.bName=bName;
		this.bContent=bContent;
		this.bDate=bDate;
		this.bHit=bHit;
		this.bGroup=bGroup;
		this.bStep=bStep;
		this.bIndent=bIndent;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbName() { 
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

	
	
}
