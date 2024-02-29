package boardModel;

public class BDto {
	int community_num;
	String title;
	String user_Id;
	String content;
	String date_created;
	int hit;
	int group_num;
	int step_num;
	int indent_num;
	
	public BDto () {
		
	}
	
	public BDto(int community_num, String user_Id, String title, String content, String date_created, int hit, int group_num, int step_num, int indent_num) {
		this.community_num=community_num;
		this.title=title;
		this.user_Id=user_Id;
		this.content=content;
		this.date_created=date_created;
		this.hit=hit;
		this.group_num=group_num;
		this.step_num=step_num;
		this.indent_num=indent_num;
	}

	public int getCommunity_num() {
		return community_num;
	}

	public void setCommunity_num(int community_num) {
		this.community_num = community_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getGroup_num() {
		return group_num;
	}

	public void setGroup_num(int group_num) {
		this.group_num = group_num;
	}

	public int getStep_num() {
		return step_num;
	}

	public void setStep_num(int step_num) {
		this.step_num = step_num;
	}

	public int getIndent_num() {
		return indent_num;
	}

	public void setIndent_num(int indent_num) {
		this.indent_num = indent_num;
	}
}
