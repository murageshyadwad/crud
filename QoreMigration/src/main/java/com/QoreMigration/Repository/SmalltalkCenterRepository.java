package com.QoreMigration.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.QoreMigration.Model.SmalltalkCenter;

@Repository
public interface SmalltalkCenterRepository extends JpaRepository<SmalltalkCenter,Long>{

	Page<SmalltalkCenter> findByNsmalltalkid(Long nsmalltalkid, Pageable pageable);

	Page<SmalltalkCenter> findByNsmalltalkidContaining(Long nsmalltalkid, Pageable pageable);

	List<SmalltalkCenter> findByNsmalltalkidContaining(Long nsmalltalkid, Sort sort);
	
	@Query(value="SELECT * FROM bl_smalltalkcenter_dtl WHERE " +
		     "account LIKE CONCAT('%',:query,'%' )"+
		      " OR guarantor LIKE CONCAT('%',:query,'%' )" +
		     " OR patientcode LIKE CONCAT('%',:query,'%' )"+
		     " OR patient LIKE CONCAT('%',:query,'%' )"+
		     " OR guarantorphoneno LIKE CONCAT('%',:query,'%' )"+
		     " OR phoneno LIKE CONCAT('%',:query,'%' )"+
		     " OR provider LIKE CONCAT('%',:query,'%' )"+
		     " OR facility LIKE CONCAT('%',:query,'%' )"+
		     " OR CAST(claimno AS TEXT) LIKE CONCAT('%',:query,'%' )"+
		     " OR CAST(dos AS TEXT) LIKE CONCAT('%',:query,'%' )"+
		     " OR companyname LIKE CONCAT('%',:query,'%' )"+
		     " OR planname LIKE CONCAT('%',:query,'%' )"+
		     " OR reportingname LIKE CONCAT('%',:query,'%' )"+
		     " OR subscriberinsgroupno LIKE CONCAT('%',:query,'%' )"+
		     " OR subscriberinsid LIKE CONCAT('%',:query,'%' )"+
		     " OR subscribername LIKE CONCAT('%',:query,'%' )"+
		     " OR CAST(subscriberdob AS TEXT) LIKE CONCAT('%',:query,'%' )"+
		     " OR planphoneno LIKE CONCAT('%',:query,'%' )"+
		     " OR charge LIKE CONCAT('%',:query,'%' )"+
		     " OR dx1code LIKE CONCAT('%',:query,'%' )"+
		     " OR dx2code LIKE CONCAT('%',:query,'%' )"+
		     " OR dx3code LIKE CONCAT('%',:query,'%' )"+
		     " OR dx4code LIKE CONCAT('%',:query,'%' )"+
		     " OR mod1code LIKE CONCAT('%',:query,'%' )"+
		     " OR mod2code LIKE CONCAT('%',:query,'%' )"+
		     " OR mod3code LIKE CONCAT('%',:query,'%' )"+
		     " OR mod4code LIKE CONCAT('%',:query,'%' )"+
		     
		     
		     "OR CAST(nsmalltalkid AS TEXT) LIKE CONCAT('%',:query,'%' )",nativeQuery = true)
		       List<SmalltalkCenter> SearchChar (String query);

}
