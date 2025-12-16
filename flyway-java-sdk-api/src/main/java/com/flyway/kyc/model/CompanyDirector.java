package com.flyway.kyc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 企业董事实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyDirector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	 ("认证类型")
	private Integer kycType;
//	 ("人脸照片")
	private List<String> facePhoto;
	//("姓名")
	private String directorName;

	//("证件类型")
	private String directorIdType;

	//("证件号")
	private String directorIdNo;
	

	//("签发机关")
	private String grantingOffice;

	//("性别")
	private Integer gender;

	//("  民族")
	private String nation;

	//(" 中文地址")
	private String address;

	//("证件开始日期")
	private String idStartDate;

	//("证件结束日期")
	private String idEndDate;

	//("国家地区")
	private String country;

	//("身份证正面")
	private String directorIdFront;
	//("身份证反面")
	private String directorIdBack;

	//(" 中文地址")
	private String enAddress;

	//("手持照片")
	private String handheldPhoto;

	//("姓名")
	private String directorNameEn;

	//(value = "出生日期")
	private String birthday;

	//("法人联系方式")
	private String legalMobile;

}
