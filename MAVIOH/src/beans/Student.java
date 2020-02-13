package beans;

import java.io.Serializable;

/**
 * 生徒を表すJavaBeans
 * @author kkiku
 */
public class Student implements Serializable {
	/** ID(主キー) */
	private int id;
	/** 名前 */
	private String name;
	/** 学年 */
	private int grade;
	/** 性別(1=男、2=女) */
	private int gender;
	/** 高校名 */
	private String school;
	/** 偏差値 */
	private float deviationValue;

	/**
	 * コンストラクタ
	 */
	public Student() { }
	/**
	 * コンストラクタ
	 * @param id ID(主キー)
	 * @param name 名前
	 * @param grade 学年
	 * @param gender 性別(1=男、2=女)
	 * @param school 高校名
	 * @param deviationValue 偏差値
	 */
	public Student(int id, String name, int grade, int gender, String school, float deviationValue) {
		this.setId(id);
		this.setName(name);
		this.setGrade(grade);
		this.setGender(gender);
		this.setSchool(school);
		this.setDeviationValue(deviationValue);
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return grade
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 * @param grade セットする grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	/**
	 * @return gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @param gender セットする gender
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @return school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school セットする school
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * @return deviationValue
	 */
	public float getDeviationValue() {
		return deviationValue;
	}
	/**
	 * @param deviationValue セットする deviationValue
	 */
	public void setDeviationValue(float deviationValue) {
		this.deviationValue = deviationValue;
	}

}
