package org.ebesson.test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;


import org.assertj.core.api.Condition;
import org.ebesson.model.Car;
import org.ebesson.model.Person;
import org.ebesson.model.Sale;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

public class SampleTest {
	
	//Persons
    private static final Person edwin = new Person("Edwin","Dalorzo", 34, true);
    private static final Person pedro = new Person("Pedro", "Aguilar", 26, true);
    private static final Person karla = new Person("Karla", "Fallas", 30, false);
    private static final Person oscar = new Person("Oscar", "Romero", 45, true);
    private static final Person franklin = new Person("Franklin", "Fallas", 25, true);
    private static final Person patricia = new Person("Patricia", "Solano", 31, false);
    private static final Person alonso = new Person("Alonso", "Dalorzo", 27, true);
    private static final Person victoria = new Person("Victoria", "Fallas", 27, false);
 
    //Cars
    private static final Car yaris = new Car("Toyota","Yaris", 2008, 15000);
    private static final Car mx5 = new Car("Mazda", "MX-5", 2009, 45000);
    private static final Car equator = new Car("Susuki","Equator", 2009, 35000);
    private static final Car x6 = new Car("BMW","X6", 2011, 88000);
    private static final Car civic = new Car("Honda","Civic", 2012, 30000);
    private static final Car tida = new Car("Nissan","Tida", 2013, 35000);
 
    //Sales
    private static final Sale edwinFromFranklin = new Sale(edwin, franklin, tida, 20000);
    private static final Sale oscarFromEdwin = new Sale(oscar, edwin, yaris, 10000);
    private static final Sale karlaFromAlonso = new Sale(karla, alonso, civic, 25000);
    private static final Sale patriciaFromPedro = new Sale(patricia, pedro, x6, 75000);
    private static final Sale victoriaFromOscar = new Sale(victoria, oscar, equator, 30000);
    private static final Sale franklinFromOscar = new Sale(franklin, oscar, mx5, 39000);
    private static final Sale alonsoFromKarla = new Sale(alonso, karla, yaris, 9000);
    private static final Sale karlaFromFranklin = new Sale(karla, franklin, mx5, 40000);
    private static final Sale edwinFromKarla = new Sale(edwin, karla, mx5, 45000);
 
    //Collections
    private static final Stream<Person> persons = new LinkedHashSet<>(asList( edwin, pedro, karla, oscar, franklin, patricia, alonso, victoria)).stream();
    private static final Stream<Car> cars = new LinkedHashSet<>(asList(yaris, mx5, equator, x6, civic, tida)).stream();
    private static final Stream<Sale> sales = new LinkedHashSet<>(asList(
            edwinFromFranklin,
            oscarFromEdwin,
            karlaFromAlonso,
            patriciaFromPedro,
            victoriaFromOscar,
            franklinFromOscar,
            alonsoFromKarla,
            karlaFromFranklin,
            edwinFromKarla
    )).stream();
    
    
    @Test
    public void shouldSelectAllToyotaCars(){
    	List<Car> toyotaCars = new ArrayList<Car>();
		
    	toyotaCars = cars.filter((Car c) -> c.getBrand().equals("Toyota"))
		                   .collect(toList());
		
		assertThat(toyotaCars).isNotEmpty().hasSize(1).contains(yaris);
		
    }
    
    @Test
    public void shouldSelectAllSalesOnToyota(){
    	List<Sale> toyotaSales = new ArrayList<Sale>();
		
		toyotaSales = sales.filter((Sale s) -> s.getCar().getBrand().equals("Toyota"))
		                   .collect(toList());
		
		assertThat(toyotaSales).isNotEmpty().hasSize(2).are(new Condition<Sale>() {

			@Override
			public boolean matches(Sale sale) {
				return "Toyota".equals(sale.getCar().getBrand());
			}
		});
		
    }
    
    @Test
    public void shouldSortByCost(){
    	List<Sale> sortedByCost = new ArrayList<Sale>();
		
		sortedByCost = sales.sorted( comparing((ToDoubleFunction<Sale>) Sale::getCost) )
		                    .collect(toList());
    }
}
