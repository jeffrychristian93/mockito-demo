package com.pji.mockitodemo.service;

/**
 * @author Jeffry Christian
 * @since 11/03/2018
 */
public class MerchantService {

    private MerchantRepository merchantRepository;

    private MerchantHistoryRepository merchantHistoryRepository;

    public void save(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public void delete(Merchant merchant) {
        merchantRepository.delete(merchant);

        MerchantHistory history = new MerchantHistory();
        history.setId(merchant.getId());
        history.setName(merchant.getName());
        history.setAddress(merchant.getAddress());

        merchantHistoryRepository.save(history);
    }

}
