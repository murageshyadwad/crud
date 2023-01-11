package com.QoreMigration.Model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bl_smalltalkcenter_dtl", schema = "public")
public class SmalltalkCenter {

	@Id
	private Long nsmalltalkid;
	private String account;
	private String guarantor;
	private String patientcode;
	private String patient;
	private Date patientdob;
	private String guarantorphoneno;
	private String phoneno;
	private String provider;
	private String facility;
	private Integer claimno;
	private Date dos;
	private String companyname;
	private String planname;
	private String reportingname;
	private String subscriberinsgroupno;
	private String subscriberinsid;
	private String subscribername;
	private Date subscriberdob;
	private String planphoneno;
	private String charge;
	private String dx1code;
	private String dx2code;
	private String dx3code;
	private String dx4code;
	private String mod1code;
	private String mod2code;
	private String mod3code;
	private String mod4code;
	private Long total;
	private Long insurancepayment;
	private Long payment;
	private Long adjustment;
	private Long remainingbalance;
	private Integer statementcount;
	private Long lastpayment;
	private Date lastpaymentdate;
	private Long totalpaymentwithinday;
	private String guarantorlastname;
	private String guarantorfirstname;
	private String lastname;
	private String firstname;
	private String businesscentername;
	private Date nextactiondate;
	private String scheduleaction;
	private String waitingforbatch;
	private Date claimlastbilleddate;
	private Long tfl;
	private Long dfl;
	private Long claimage;
	private String reasoncodes;
	private String remarkcodes;
	private Long mastertransactionid;
	private Long mastertransactiondetailid;
	private Long transactionid;
	private Long transactiondetailid;
	private Long companyid;
	private Long planid;
	private Long providerid;
	private String gender;
	private Date lastworkeddate;
	private String lastclaimnote;
	private String standardscheduleaction;
	private Long nrenderringproviderid;
	private String srenderringprovidername;

	public Long getNsmalltalkid() {
		return nsmalltalkid;
	}

	public void setNsmalltalkid(Long nsmalltalkid) {
		this.nsmalltalkid = nsmalltalkid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	public String getPatientcode() {
		return patientcode;
	}

	public void setPatientcode(String patientcode) {
		this.patientcode = patientcode;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public Date getPatientdob() {
		return patientdob;
	}

	public void setPatientdob(Date patientdob) {
		this.patientdob = patientdob;
	}

	public String getGuarantorphoneno() {
		return guarantorphoneno;
	}

	public void setGuarantorphoneno(String guarantorphoneno) {
		this.guarantorphoneno = guarantorphoneno;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Integer getClaimNo() {
		return claimno;
	}

	public void setClaimNo(Integer claimNo) {
		claimno = claimNo;
	}

	public Date getDos() {
		return dos;
	}

	public void setDos(Date dos) {
		this.dos = dos;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public String getReportingname() {
		return reportingname;
	}

	public void setReportingname(String reportingname) {
		this.reportingname = reportingname;
	}

	public String getSubscriberinsgroupno() {
		return subscriberinsgroupno;
	}

	public void setSubscriberinsgroupno(String subscriberinsgroupno) {
		this.subscriberinsgroupno = subscriberinsgroupno;
	}

	public String getSubscriberinsid() {
		return subscriberinsid;
	}

	public void setSubscriberinsid(String subscriberinsid) {
		this.subscriberinsid = subscriberinsid;
	}

	public String getSubscribername() {
		return subscribername;
	}

	public void setSubscribername(String subscribername) {
		this.subscribername = subscribername;
	}

	public Date getSubscriberdob() {
		return subscriberdob;
	}

	public void setSubscriberdob(Date subscriberdob) {
		this.subscriberdob = subscriberdob;
	}

	public String getPlanphoneno() {
		return planphoneno;
	}

	public void setPlanphoneno(String planphoneno) {
		this.planphoneno = planphoneno;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getDx1code() {
		return dx1code;
	}

	public void setDx1code(String dx1code) {
		this.dx1code = dx1code;
	}

	public String getDx2code() {
		return dx2code;
	}

	public void setDx2code(String dx2code) {
		this.dx2code = dx2code;
	}

	public String getDx3code() {
		return dx3code;
	}

	public void setDx3code(String dx3code) {
		this.dx3code = dx3code;
	}

	public String getDx4code() {
		return dx4code;
	}

	public void setDx4code(String dx4code) {
		this.dx4code = dx4code;
	}

	public String getMod1code() {
		return mod1code;
	}

	public void setMod1code(String mod1code) {
		this.mod1code = mod1code;
	}

	public String getMod2code() {
		return mod2code;
	}

	public void setMod2code(String mod2code) {
		this.mod2code = mod2code;
	}

	public String getMod3code() {
		return mod3code;
	}

	public void setMod3code(String mod3code) {
		this.mod3code = mod3code;
	}

	public String getMod4code() {
		return mod4code;
	}

	public void setMod4code(String mod4code) {
		this.mod4code = mod4code;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getInsurancepayment() {
		return insurancepayment;
	}

	public void setInsurancepayment(Long insurancepayment) {
		this.insurancepayment = insurancepayment;
	}

	public Long getPayment() {
		return payment;
	}

	public void setPayment(Long payment) {
		this.payment = payment;
	}

	public Long getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(Long adjustment) {
		this.adjustment = adjustment;
	}

	public Long getRemainingbalance() {
		return remainingbalance;
	}

	public void setRemainingbalance(Long remainingbalance) {
		this.remainingbalance = remainingbalance;
	}

	public Integer getStatementcount() {
		return statementcount;
	}

	public void setStatementcount(Integer statementcount) {
		this.statementcount = statementcount;
	}

	public Long getLastpayment() {
		return lastpayment;
	}

	public void setLastpayment(Long lastpayment) {
		this.lastpayment = lastpayment;
	}

	public Date getLastpaymentdate() {
		return lastpaymentdate;
	}

	public void setLastpaymentdate(Date lastpaymentdate) {
		this.lastpaymentdate = lastpaymentdate;
	}

	public double getTotalpaymentwithinday() {
		return totalpaymentwithinday;
	}

	public void setTotalpaymentwithinday(Long totalpaymentwithinday) {
		this.totalpaymentwithinday = totalpaymentwithinday;
	}

	public String getGuarantorlastname() {
		return guarantorlastname;
	}

	public void setGuarantorlastname(String guarantorlastname) {
		this.guarantorlastname = guarantorlastname;
	}

	public String getGuarantorfirstname() {
		return guarantorfirstname;
	}

	public void setGuarantorfirstname(String guarantorfirstname) {
		this.guarantorfirstname = guarantorfirstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getBusinesscentername() {
		return businesscentername;
	}

	public void setBusinesscentername(String businesscentername) {
		this.businesscentername = businesscentername;
	}

	public Date getNextactiondate() {
		return nextactiondate;
	}

	public void setNextactiondate(Date nextactiondate) {
		this.nextactiondate = nextactiondate;
	}

	public String getScheduleaction() {
		return scheduleaction;
	}

	public void setScheduleaction(String scheduleaction) {
		this.scheduleaction = scheduleaction;
	}

	public String getWaitingforbatch() {
		return waitingforbatch;
	}

	public void setWaitingforbatch(String waitingforbatch) {
		this.waitingforbatch = waitingforbatch;
	}

	public Date getClaimlastbilleddate() {
		return claimlastbilleddate;
	}

	public void setClaimlastbilleddate(Date claimlastbilleddate) {
		this.claimlastbilleddate = claimlastbilleddate;
	}

	public Long getTfl() {
		return tfl;
	}

	public void setTfl(Long tfl) {
		this.tfl = tfl;
	}

	public Long getDfl() {
		return dfl;
	}

	public void setDfl(Long dfl) {
		this.dfl = dfl;
	}

	public Long getClaimage() {
		return claimage;
	}

	public void setClaimage(Long claimage) {
		this.claimage = claimage;
	}

	public String getReasoncodes() {
		return reasoncodes;
	}

	public void setReasoncodes(String reasoncodes) {
		this.reasoncodes = reasoncodes;
	}

	public String getRemarkcodes() {
		return remarkcodes;
	}

	public void setRemarkcodes(String remarkcodes) {
		this.remarkcodes = remarkcodes;
	}

	public Long getMastertransactionid() {
		return mastertransactionid;
	}

	public void setMastertransactionid(Long mastertransactionid) {
		this.mastertransactionid = mastertransactionid;
	}

	public Long getMastertransactiondetailid() {
		return mastertransactiondetailid;
	}

	public void setMastertransactiondetailid(Long mastertransactiondetailid) {
		this.mastertransactiondetailid = mastertransactiondetailid;
	}

	public Long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}

	public Long getTransactiondetailid() {
		return transactiondetailid;
	}

	public void setTransactiondetailid(Long transactiondetailid) {
		this.transactiondetailid = transactiondetailid;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public Long getPlanid() {
		return planid;
	}

	public void setPlanid(Long planid) {
		this.planid = planid;
	}

	public Long getProviderid() {
		return providerid;
	}

	public void setProviderid(Long providerid) {
		this.providerid = providerid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLastworkeddate() {
		return lastworkeddate;
	}

	public void setLastworkeddate(Date lastworkeddate) {
		this.lastworkeddate = lastworkeddate;
	}

	public String getLastclaimnote() {
		return lastclaimnote;
	}

	public void setLastclaimnote(String lastclaimnote) {
		this.lastclaimnote = lastclaimnote;
	}

	public String getStandardscheduleaction() {
		return standardscheduleaction;
	}

	public void setStandardscheduleaction(String standardscheduleaction) {
		this.standardscheduleaction = standardscheduleaction;
	}

	public Long getNrenderringproviderid() {
		return nrenderringproviderid;
	}

	public void setNrenderringproviderid(Long nrenderringproviderid) {
		this.nrenderringproviderid = nrenderringproviderid;
	}

	public String getSrenderringprovidername() {
		return srenderringprovidername;
	}

	public void setSrenderringprovidername(String srenderringprovidername) {
		this.srenderringprovidername = srenderringprovidername;
	}

}
