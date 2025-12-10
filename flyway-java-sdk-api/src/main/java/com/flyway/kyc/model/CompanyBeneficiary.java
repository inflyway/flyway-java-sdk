package com.flyway.kyc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 股东认证信息
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CompanyBeneficiary implements Serializable {

	private static final long serialVersionUID = 1L;
	 
//	 ("认证类型")
	private Integer kycType;
//	 ("人脸照片")
	private List<String> facePhoto;
	//("姓名")
	private String beneficiaryName;

	//("姓名")
	private String beneficiaryNameEn;

	//("证件类型")
	private String beneficiaryIdType;

	//("证件号")
	private String beneficiaryIdNo;

	//("证件资料")
	private List<String> certificateInfo;

	//("受益人住址材料证明资料")
	private String addressCertificateInfo;

	//("股权证明资料")
	private String shareholdCertificateInfo;
	/**
	 * 活体检测照片
	 */
	//(" 照片")
	private String vivoImgUrl;

	//("签发机关")
	private String grantingOffice;

	//("性别")
	private Integer gender;

	//("  民族")
	private String nation;

	//(" 中文地址")
	private String address;

	//(" 中文地址")
	private String enAddress;

	//("证件开始日期")
	private String idStartDate;

	//("证件结束日期")
	private String idEndDate;

	//("国家地区")
	private String country;

	//("身份证正面")
	private String beneficiaryIdFront;
	//("身份证反面")

	private String beneficiaryIdBack;

	//("持股比例")

	private String ratio;

	//("收益人说明书")

	private String beneficiaryStatement;

	//(value = "出生日期")
	private String birthday;

	//("手持照片")
	private String handheldPhoto;
}
