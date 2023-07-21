package guruspringframework.sdjpamultidb.services;

import guruspringframework.sdjpamultidb.domain.cardholder.CreditCardHolder;
import guruspringframework.sdjpamultidb.domain.creditcard.CreditCard;
import guruspringframework.sdjpamultidb.domain.pan.CreditCardPAN;
import guruspringframework.sdjpamultidb.repositories.cardholder.CreditCardHolderRepository;
import guruspringframework.sdjpamultidb.repositories.creditcard.CreditCardRepository;
import guruspringframework.sdjpamultidb.repositories.pan.CreditCardPANRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

  private final CreditCardRepository ccRepository;
  private final CreditCardHolderRepository ccHolderRepository;
  private final CreditCardPANRepository ccPanRepository;

  @Transactional
  @Override
  public CreditCard getCreditCardById(Long id) {
    CreditCard cc = ccRepository.findById(id).orElseThrow();
    CreditCardPAN ccPan = ccPanRepository.findById(id).orElseThrow();
    CreditCardHolder ccHolder = ccHolderRepository.findById(id).orElseThrow();

    cc.setCreditCardNumber(ccPan.getCreditCardNumber());
    cc.setFirstName(ccHolder.getFirstName());
    cc.setLastName(ccHolder.getLastName());
    cc.setZipCode(ccHolder.getZipCode());

    return cc;
  }

  @Override
  @Transactional
  public CreditCard saveCreditCard(CreditCard cc) {
    CreditCard savedCC = ccRepository.save(cc);
    savedCC.setCreditCardNumber(cc.getCreditCardNumber());
    savedCC.setFirstName(cc.getFirstName());
    savedCC.setLastName(cc.getLastName());
    savedCC.setZipCode(cc.getZipCode());

    ccPanRepository.save(CreditCardPAN.builder()
        .creditCardId(savedCC.getId())
        .creditCardNumber(savedCC.getCreditCardNumber())
        .build());

    ccHolderRepository.save(CreditCardHolder.builder()
        .creditCardId(savedCC.getId())
        .firstName(savedCC.getFirstName())
        .lastName(savedCC.getLastName())
        .zipCode(savedCC.getZipCode())
        .build());

    return savedCC;
  }
}
