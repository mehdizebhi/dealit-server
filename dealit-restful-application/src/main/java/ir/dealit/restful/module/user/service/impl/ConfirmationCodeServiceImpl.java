package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.module.user.entity.ConfirmationCodeEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.ConfirmationCodeRepository;
import ir.dealit.restful.module.user.service.ConfirmationCodeService;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private final ConfirmationCodeRepository confirmationCodeRepository;

    @Override
    public String newOTPCode(String reason, UserEntity user) {
        int number = new Random().nextInt(999999);
        String code = String.format("%06d", number);
        var confirmationCode = ConfirmationCodeEntity.builder()
                .code(code)
                .expireAt(DateTime.now().plusMinutes(5).toDate())
                .reason(reason)
                .user(user)
                .used(false)
                .build();

        confirmationCode = confirmationCodeRepository.save(confirmationCode);
        return confirmationCode.getCode();
    }
}
