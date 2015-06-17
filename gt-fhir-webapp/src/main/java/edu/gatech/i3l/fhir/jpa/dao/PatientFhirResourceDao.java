package edu.gatech.i3l.fhir.jpa.dao;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.uhn.fhir.jpa.dao.DaoMethodOutcome;
import ca.uhn.fhir.jpa.dao.IDaoListener;
import ca.uhn.fhir.jpa.entity.TagTypeEnum;
import ca.uhn.fhir.model.api.TagList;
import ca.uhn.fhir.model.dstu2.composite.MetaDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.server.IBundleProvider;
import edu.gatech.i3l.jpa.model.omop.IResourceTable;
import edu.gatech.i3l.jpa.model.omop.ext.PatientFhirExtTable;

@Transactional(propagation = Propagation.REQUIRED)
public class PatientFhirResourceDao extends BaseFhirResourceDao<Patient>{

	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(PatientFhirResourceDao.class);

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager myEntityManager;

//	private String myResourceName;
//	private Class<Patient> myResourceType;

	public PatientFhirResourceDao() {
		setResourceTable(PatientFhirExtTable.class);
	}
	
	@Override
	public void registerDaoListener(IDaoListener theListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTag(IdDt theId, TagTypeEnum theTagType, String theScheme,
			String theTerm, String theLabel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DaoMethodOutcome delete(IdDt theResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome deleteByUrl(String theString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagList getAllResourceTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagList getTags(IdDt theResourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBundleProvider history(Date theSince) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBundleProvider history(IdDt theId, Date theSince) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBundleProvider history(Long theId, Date theSince) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void removeTag(IdDt theId, TagTypeEnum theTagType, String theScheme,
			String theTerm) {
		// TODO Auto-generated method stub
		
	}

	@Override

	public MetaDt metaGetOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaDt metaGetOperation(IdDt theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaDt metaDeleteOperation(IdDt theId1, MetaDt theMetaDel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaDt metaAddOperation(IdDt theId1, MetaDt theMetaAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome create(Patient theResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome create(Patient theResource, String theIfNoneExist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome create(Patient theResource, String theIfNoneExist,
			boolean thePerformIndexing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome update(Patient theResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome update(Patient theResource, String theMatchUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoMethodOutcome update(Patient theResource, String theMatchUrl,
			boolean thePerformIndexing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate translatePredicateDateLessThan(String theParamName, Date upperBound, Root<? extends IResourceTable> from, CriteriaBuilder theBuilder) {
		Calendar c = Calendar.getInstance();
		c.setTime(upperBound);
		Predicate ub = null;
		switch (theParamName) {
		case Patient.SP_DEATHDATE:
			ub = theBuilder.lessThanOrEqualTo(from.get("death_date").as(Date.class), upperBound);
			break;
		case Patient.SP_BIRTHDATE:
			Predicate lt1 = theBuilder.lessThan(from.get("yearOfBirth").as(Integer.class), c.get(Calendar.YEAR));
			Predicate lt2 = theBuilder.and( theBuilder.equal(from.get("yearOfBirth").as(Integer.class), c.get(Calendar.YEAR)),
					theBuilder.lessThan(from.get("monthOfBirth").as(Integer.class), c.get(Calendar.MONTH)));
			Predicate lt3 = theBuilder.and( theBuilder.equal(from.get("yearOfBirth").as(Integer.class), c.get(Calendar.YEAR)),
					theBuilder.equal(from.get("monthOfBirth").as(Integer.class), c.get(Calendar.MONTH)),
					theBuilder.lessThanOrEqualTo(from.get("dayOfBirth").as(Integer.class), c.get(Calendar.DAY_OF_MONTH)));
			ub = theBuilder.or(lt1, lt2, lt3);
			
			break;	
		default:
			break;
		}
		return ub;
	}

	@Override
	public Predicate translatePredicateDateGreaterThan(String theParamName,
			Date lowerBound, Root<? extends IResourceTable> from, CriteriaBuilder theBuilder) {
		Calendar c = Calendar.getInstance();
		c.setTime(lowerBound);
		Predicate lb = null;
		switch (theParamName) {
		case Patient.SP_DEATHDATE:
			lb = theBuilder.greaterThanOrEqualTo(from.get("death_date").as(Date.class), lowerBound);
			break;
		case Patient.SP_BIRTHDATE:
			Predicate gt1 = theBuilder.greaterThan(from.get("yearOfBirth").as(Integer.class), c.get(Calendar.YEAR));
			Predicate gt2 = theBuilder.and( theBuilder.equal(from.get("yearOfBirth").as(Integer.class), c.get(Calendar.YEAR)),
											theBuilder.greaterThan(from.get("monthOfBirth").as(Integer.class), c.get(Calendar.MONTH)));
			Predicate gt3 = theBuilder.and( theBuilder.equal(from.get("yearOfBirth").as(Integer.class), c.get(Calendar.YEAR)),
											theBuilder.equal(from.get("monthOfBirth").as(Integer.class), c.get(Calendar.MONTH)),
											theBuilder.greaterThanOrEqualTo(from.get("dayOfBirth").as(Integer.class), c.get(Calendar.DAY_OF_MONTH)));
			lb = theBuilder.or(gt1, gt2, gt3);
			break;	
		default:
			break;
		}
		return lb;
	}

	@Override
	public Predicate translatePredicateString(String theParamName, String likeExpression, Root<? extends IResourceTable> from, CriteriaBuilder theBuilder) {
		Predicate singleCode = null;
		switch (theParamName) {
		case Patient.SP_ADDRESS:
			Predicate p1 = theBuilder.like(from.get("location").get("address1").as(String.class), likeExpression);
			Predicate p2 = theBuilder.like(from.get("location").get("address2").as(String.class), likeExpression);
			Predicate p3 = theBuilder.like(from.get("location").get("city").as(String.class), likeExpression);
			Predicate p4 = theBuilder.like(from.get("location").get("state").as(String.class), likeExpression);
			Predicate p5 = theBuilder.like(from.get("location").get("zipCode").as(String.class), likeExpression);
			Predicate p6 = theBuilder.like(from.get("location").get("country").as(String.class), likeExpression);
			singleCode = theBuilder.or(p1, p2, p3, p4, p5, p6);
			break;

		default:
			break;
		}
		return singleCode;
	}
}