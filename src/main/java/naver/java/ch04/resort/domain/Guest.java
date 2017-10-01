package naver.java.ch04.resort.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guest {
	private Integer id;
	private Integer grade;
	private String name;
	private String company;
	
	public Guest() {}

	public Guest(Integer id, Integer grade, String name, String company) {
		this.id = id;
		this.grade = grade;
		this.name = name;
		this.company = company;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public Integer getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", grade=" + grade + ", name=" + name
				+ ", company=" + company + "]";
	}
	
}
