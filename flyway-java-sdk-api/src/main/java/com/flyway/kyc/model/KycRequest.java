package com.flyway.kyc.model;

import com.flyway.common.model.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * kyc实名请求参数
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class KycRequest extends CommonRequest {

	 

	// ("请求流水号")
	private String requestNo;
	// ("申请人手机号")
	private String applicantPhone;
	// ("申请人邮箱")
	private String applicantEmail; 
	// ("公司证件号")
	private String companyCode;
 
	// ("国家")
	private String country;

	// ("公司中文名称")
	private String companyName;

	// ("公司英文名称")
	private String companyEnName;

	// ("证件类型")
	private String idType;

	// ("注册日期")
	private String registerAt;

	// ("企业主体类型")
	private Integer subjectType;

	// (value = "证件资料照片(香港周年证明书)")
	private String annualAuditTable;

	// (value = "证件资料照片(香港法团成立表)")
	private String establishedPhoto;

	// ("证件资料照片")
	private String businessLicense;

	// ("经营地址")
	private String businessAddress;

	// ("经营英文地址")
	private String businessEnAddress;

	// ("证件资料照片(香港商业登记证)")
	private String businessRegiCert;
	// (value = "股权架构图地址")
	private String certificateInfo5;

	// ("最新年审报告")
	private String latestAuditReport;

	// ("CTC文件")
	private String ctcFile;
	// ("近3个月的银行对账单和流水 ")
	private String bankStatement3Months;

	// (value = "香港商业登记证编号")
	private String businessRegistrationNumber;

	// ("额外董事")
	private List<CompanyDirector> companyDirectorDos;
	// ("收益人")
	private List<CompanyBeneficiary> companyBeneficiaryDos;

	// (value = "主要销售国家地区")

	private Map<String, String> mainSalesRegion;

	// (value = "主营产品分类")
	private Map<String, String> mainProductClass;

	// (value = "商户经营所属行业")
	private String userIndustry;

	// (value="懂事变更")
	public String legalChange;

	// (value="懂事通知书")
	public String legalNotice;

	private String noticeUrl;
}