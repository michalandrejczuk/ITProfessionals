package com.spring.itprofessionals.dao;

import com.spring.itprofessionals.entity.ITProfessional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ITProfessionalDAOImplTest {

    @Mock
    private EntityManager entityManager;

    private ITProfessionalDAOImpl cut;

    private ITProfessional itProfessional1;

    private ITProfessional itProfessional2;

    private List<ITProfessional> itProfessionals = new ArrayList<>();

    private List<String> technologies = List.of("technology1", "technology2");

    @BeforeEach
    void setUp() {
        cut = new ITProfessionalDAOImpl(entityManager);
        itProfessional1 =  new ITProfessional("firsName1", "lastName1", "email1", "specialty1", technologies,
                "country1", new BigDecimal(1), "currency1");
        itProfessional1.setId(1);
        itProfessional2 = new ITProfessional("firsName2", "lastName2", "email2", "specialty2", technologies,
                "country2", new BigDecimal(2), "currency2");
        itProfessional2.setId(2);
        itProfessionals.add(itProfessional1);
        itProfessionals.add(itProfessional2);

    }

    @Test
    void findAll() {
        TypedQuery<ITProfessional> mockedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("from ITProfessional", ITProfessional.class)).thenReturn(mockedQuery);
        when(mockedQuery.getResultList()).thenReturn(itProfessionals);

        List<ITProfessional> result = cut.findAll();
        assertEquals(itProfessionals.size(), result.size());
        assertTrue(result.containsAll(itProfessionals));
    }

    @Test
    void findById() {
        when(entityManager.find(ITProfessional.class, 1)).thenReturn(itProfessional1);
        ITProfessional result = cut.findById(1);
        assertNotNull(result);
        assertEquals(itProfessional1, result);
    }

    @Test
    void save() {
        cut.save(itProfessional1);
        verify(entityManager, times(1)).merge(itProfessional1);
    }

    @Test
    void deleteById() {
        when(entityManager.find(ITProfessional.class, 1)).thenReturn(itProfessional1);
        cut.deleteById(1);
        verify(entityManager, times(1)).remove(itProfessional1);
    }

    @Test
    void findById_WithInvalidId_ShouldReturnNull() {
        when(entityManager.find(ITProfessional.class, 999)).thenReturn(null);
        ITProfessional result = cut.findById(999);
        assertNull(result);
    }

    @Test
    void save_WithNullProfessional_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            cut.save(null);
        });
    }
    @Test
    void deleteById_WithInvalidId_ShouldNotDeleteAnyProfessional() {
        when(entityManager.find(ITProfessional.class, 999)).thenReturn(null);
        cut.deleteById(999);
        verify(entityManager, times(0)).remove(any());
    }

}