package infoModel;

public class PoliceStationDto {

	private int id;
	private String police_office;
	private String district;
	private String sub_district;
	private String department;
	private String tel;
	private String address;

	public PoliceStationDto() {}

	public PoliceStationDto(int id, String police_office, String district, String sub_district, String department,
			String tel, String address) {
		this.id = id;
		this.police_office = police_office;
		this.district = district;
		this.sub_district = sub_district;
		this.department = department;
		this.tel = tel;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolice_office() {
		return police_office;
	}

	public void setPolice_office(String police_office) {
		this.police_office = police_office;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSub_district() {
		return sub_district;
	}

	public void setSub_district(String sub_district) {
		this.sub_district = sub_district;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
