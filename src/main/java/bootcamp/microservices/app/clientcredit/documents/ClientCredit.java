package bootcamp.microservices.app.clientcredit.documents;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
@Document
public class ClientCredit {

	private String id;

	private String creditNumber;

	private Client client;

	private Double creditAmount;

	private Date disbursementDay;

	private Date paymentDay;

	private Integer loanPeriod;

	private Integer gracePeriod;

	private Double creditInterestRate;

	private Double creditoMoratoriumInterestRate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createDate;

	private String createUser;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date modifyDate;

	private String modifyUser;

	private Integer status;

}
