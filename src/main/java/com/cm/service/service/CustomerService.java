package com.cm.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cm.service.controller.ResponseHelper;
import com.cm.service.models.City;
import com.cm.service.models.Country;
import com.cm.service.models.Customer;
import com.cm.service.models.CustomerAddress;
import com.cm.service.models.CustomerAddressModel;
import com.cm.service.models.CustomerEmail;
import com.cm.service.models.CustomerMain;
import com.cm.service.models.CustomerModel;
import com.cm.service.models.CustomerPhoneNumber;
import com.cm.service.models.State;
import com.cm.service.repository.AddressRepository;
import com.cm.service.repository.CityRepository;
import com.cm.service.repository.CountryRepository;
import com.cm.service.repository.CustomerEmailRepository;
import com.cm.service.repository.CustomerModelRepository;
import com.cm.service.repository.CustomerPhoneNumberRepository;
import com.cm.service.repository.CustomerRepository;
import com.cm.service.repository.StateRepository;
/**
 * 
 * @author Priyanka
 *
 */
@Service
public class CustomerService extends ResponseHelper {
	@Autowired
	private CustomerRepository custRepo;
	@Autowired
	private AddressRepository addrRepo;
	@Autowired
	private CustomerEmailRepository emailRepo;
	@Autowired
	private CustomerPhoneNumberRepository phoneRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CountryRepository countryRepo;

	/**
	 * Saves customer personal details as Customer entity
	 * 
	 * @param customerMain
	 * @return
	 */
	public CustomerMain addCustomer(Customer customer) {
		// Customer customer =
		// Customer.fromPersonalDetails(customerMain.getPersonalDetails());
		Customer response = custRepo.save(customer);
		/*
		 * if(response != null) { String customerId = response.getId();
		 * 
		 * List<CustomerAddress> addresses =
		 * fromCustomerAddressModel(customerMain.getAddresses()); addItems(customerId,
		 * addresses, addrRepo);
		 * 
		 * List<CustomerEmail> emails = customerMain.getEmails(); addItems(customerId,
		 * emails, emailRepo);
		 * 
		 * List<CustomerPhoneNumber> phoneNumbers = customerMain.getPhoneNumbers();
		 * addItems(customerId, phoneNumbers, phoneRepo); }
		 */
		return toCustomerMain(response);
	}

	/**
	 * Saves customer's addresses
	 * 
	 * @param customerAddresses
	 * @param customerId
	 * @return
	 */
	public List<CustomerAddress> addOrUpdateCustomerAddress(List<CustomerAddress> customerAddresses,
			String customerId) {
		validateCustomerId(customerId);
		for (CustomerAddress ca : customerAddresses) {
			getBody(cityRepo.findByCityCode(ca.getCity()), INVALID_CITY_CODE);
		}
		return addrRepo.saveAll(customerAddresses);
	}

	/**
	 * Saves customer's email addresses
	 * 
	 * @param customerEmails
	 * @param customerId
	 * @return
	 */
	public List<CustomerEmail> addOrUpdateCustomerEmails(List<CustomerEmail> customerEmails, String customerId) {
		validateCustomerId(customerId);
		return emailRepo.saveAll(customerEmails);
	}

	/**
	 * Saves customer's phone numbers
	 * 
	 * @param customerPhoneNumbers
	 * @param customerId
	 * @return
	 */
	public List<CustomerPhoneNumber> addOrUpdateCustomerPhoneNumbers(List<CustomerPhoneNumber> customerPhoneNumbers, String customerId) {
		validateCustomerId(customerId);
		return phoneRepo.saveAll(customerPhoneNumbers);
	}

	/**
	 * Retrieves all customer records including their addresses, emails and phone
	 * numbers
	 * 
	 * @return
	 */
	public List<CustomerMain> allCustomers() {
		List<Customer> customers = custRepo.findAll();
		if (CollectionUtils.isEmpty(customers)) {
			return null;
		}
		List<CustomerMain> allCustomers = new ArrayList<>();
		for (Customer cust : customers) {

			allCustomers.add(toCustomerMain(cust));
		}
		return allCustomers;
	}
	/**
	 * Retrieves all addresses associated to a customer
	 * @param customerId
	 * @return
	 */
	public List<CustomerAddress> getCustomerAddress(String customerId) {
		validateCustomerId(customerId);
		return addrRepo.findAllByCustomerId(customerId).get();
	}
	/**
	 * Retrieves all email addresses associated to a customer
	 * 
	 * @param customerId
	 * @return
	 */
	public List<CustomerEmail> getCustomerEmails(String customerId) {
		validateCustomerId(customerId);
		return emailRepo.findAllByCustomerId(customerId).get();
	}
	/**
	 * Retrieves all phone numbers associated to a customer
	 * 
	 * @param customerId
	 * @return
	 */
	public List<CustomerPhoneNumber> getCustomerPhoneNumbers(String customerId) {
		validateCustomerId(customerId);
		return phoneRepo.findAllByCustomerId(customerId).get();
	}
	private CustomerMain toCustomerMain(Customer customer) {
		CustomerMain cm = new CustomerMain();
		cm.setPersonalDetails(customer.toPersonalDetails());

		String customerId = customer.getId();
		List<CustomerAddress> addresses = getAllItems(customerId, addrRepo);
		cm.setAddresses(toAddressModels(addresses));

		cm.setEmails(getAllItems(customerId, emailRepo));
		cm.setPhoneNumbers(getAllItems(customerId, phoneRepo));

		return cm;
	}

	@Deprecated
	protected <T extends CustomerModel> List<T> addItems(String customerId, List<T> items,
			CustomerModelRepository<T, String> repo) {
		if (items == null) {
			return null;
		}
		for (T model : items) {
			model.setCustomerId(customerId);
		}
		return repo.insert(items);
	}

	private <T extends CustomerModel> List<T> getAllItems(String customerId, CustomerModelRepository<T, String> repo) {
		return repo.findAllByCustomerId(customerId).get();
	}

	private List<CustomerAddressModel> toAddressModels(List<CustomerAddress> addresses) {
		List<CustomerAddressModel> customerAddressModels = new ArrayList<>();
		for (CustomerAddress customerAddress : addresses) {
			CustomerAddressModel customerAddressModel = customerAddress.toCustomerAddressModel();

			Optional<City> foundCity = cityRepo.findByCityCode(customerAddress.getCity());
			City city = getBody(foundCity, INVALID_CITY_CODE);
			customerAddressModel.setCity(city);

			Optional<State> foundState = stateRepo.findByStateCode(city.getStateCode());
			State state = getBody(foundState, INVALID_STATE_CODE);
			customerAddressModel.setState(state);

			Optional<Country> foundCountry = countryRepo.findByCountryCode(state.getCountryCode());
			Country country = getBody(foundCountry, INVALID_COUNTRY_CODE);
			customerAddressModel.setCountry(country);
			customerAddressModels.add(customerAddressModel);
		}
		return customerAddressModels;
	}
	
	private void validateCustomerId(String customerId) {
		getBody(custRepo.findById(customerId), INVALID_CUSTOMER_ID);
	}


}
