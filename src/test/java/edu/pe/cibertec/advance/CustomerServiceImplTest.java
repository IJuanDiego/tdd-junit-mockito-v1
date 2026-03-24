package edu.pe.cibertec.advance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Activar la extension de mockito
@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerServiceImpl - Unit Test With Mockito")
public class CustomerServiceImplTest {

    //Crear un objeto falso de CustomerRepository (No afecta yconecta con BD)
    @Mock //Mockea la clase, no afecta el funcionamiento de la aplicacion
    private CustomerRepository repository;

    //Inyeccion del mock repository
    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    @DisplayName("Returns All Customers from repository")
    void givenCustomersExists_whenGetAllCustomers_thenReturnsList(){

        //ARRANGE: Prepara la data
        List<Customer> customers = List.of(
                new Customer(1L,"Ana Lucia","ana@gmail.com"),
                new Customer(2L,"Pedro Javier","pedro@gmail.com")
        );

        //ACT: Cuando se llama al findAll del repository, el mock retorna la lista
        when(repository.findAll()).thenReturn(customers);
            //No ejecuta esto//  //Se ejecuta esto (mock)//

        //Llamar al metodo que estamos testeando
        List<Customer> result = service.getAllCustomer();

        //ASSERT: Verificar los resultados
        assertEquals(2, result.size());

        //Verificar la llamada del findAll
        verify(repository, times(1)).findAll();
    }

     ///getCustomerById(Long Id)
    @Test
    @DisplayName("Returns Customer when ID exists")
    void givenExistingId_whenGetCustomerById_thenReturnCustomer(){
         Customer customer = new Customer(1L, "Juan Perez", "jperez@gmail.com");

         when(repository.findById(1L)).thenReturn(Optional.of(customer));

         Customer result = service.getCustomerById(1L);

         assertEquals("Juan Perez", result.getName());

         verify(repository, times(1)).findById(1L);
    };

     ///getCustomerByEmail
     /*@Test
     @DisplayName("Returns Customer when ID exists")
     void givenExistingEmail_whenGetCustomerByEmail_thenReturnCustomer(){
         Customer customer = new Customer(1L, "Juan Perez", "jperez@gmail.com");

         when(repository.findById(1L)).thenReturn(Optional.of(customer));

         Customer result = service.getCustomerById(1L);

         assertEquals("Juan Perez", result.getName());

         verify(repository, times(1)).findById(1L);
     };*/

     /// CustomerNotFoundException
    @Test
    @DisplayName("Throws CustomerNotFoundException when ID does not exist")
    void givenNonExistingId_whenGetCustomerById_C(){

        when(repository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, ()-> service.getCustomerById(100L));
        verify(repository, times(1)).findById(100L);
    }

    /// deleteCustomer
    @Test
    @DisplayName("Delete Customer by ID")
    void givenDeleteById_whenDeleteCustomer_thenReturnCustomer(){

        Customer customer = new Customer(1L,"Ana Lucia","ana@gmail.com");

        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        service.deleteCustomer(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    /// Throws exception when deleting non-existing ID
    @Test
    @DisplayName("Throws exception when deleting non-existing ID")
    void givenNonExistingId_whenDeleteCustomer_thenThrowsCustomerNotFoundException(){

        when(repository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, ()-> service.deleteCustomer(100L));
        verify(repository, times(1)).findById(100L);
        verify(repository, never()).deleteById(100L);
        ///verify(repository,times(0)).deleteById(100L);
    }


}
