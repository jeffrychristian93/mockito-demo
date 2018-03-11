package com.pji.mockitodemo;

import com.pji.mockitodemo.service.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Jeffry Christian
 * @since 11/03/2018
 * @version 1.0
 */
public class MerchantServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private MerchantHistoryRepository merchantHistoryRepository;

    @Captor
    private ArgumentCaptor<MerchantHistory> merchantHistoryArgumentCaptor;

    @InjectMocks
    private MerchantService merchantService;

    @Test
    public void testSave() {
        Merchant merchant = new Merchant();
        merchant.setId(1);
        merchant.setName("Jeffry");
        merchant.setAddress("Kudus");

        merchantService.save(merchant);

        verify(merchantRepository).save(merchant);
    }

    @Test
    public void testDelete() {
        Merchant merchant = new Merchant();
        merchant.setId(1);
        merchant.setName("Jeffry");
        merchant.setAddress("Kudus");

        merchantService.delete(merchant);

        verify(merchantRepository).delete(merchant);
        verify(merchantHistoryRepository).save(merchantHistoryArgumentCaptor.capture());
//        verify(merchantHistoryRepository).save(any());
//        verify(merchantHistoryRepository).save(any(MerchantHistory.class));

        MerchantHistory history = merchantHistoryArgumentCaptor.getValue();
        assertEquals("Jeffry", history.getName());
        assertEquals("Kudus", history.getAddress());

    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(merchantRepository, merchantHistoryRepository);
    }
}
