package com.blessing.todo.delegate;

import com.blessing.todo.api.AccountApiDelegate;
import com.blessing.todo.entity.Account;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.mapper.AccountMapper;
import com.blessing.todo.model.AccountDTO;
import com.blessing.todo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountApiDelegateImpl implements AccountApiDelegate {

    private AccountService accountService;

    @Override
    public ResponseEntity<AccountDTO> findAccountById(Long userId) {
        final Account account = accountService.findById(userId).orElseThrow(() -> new DataNotFoundException("Account not found"));
        return ResponseEntity.ok(
                AccountMapper.INSTANCE.internalToDTO(account)
        );
    }

    @Override
    public ResponseEntity<Void> updateAccount(AccountDTO accountDTO) {
        accountService.update(
                AccountMapper.INSTANCE.dtoToInternal(accountDTO)
        );
        return ResponseEntity.noContent().build();
    }
}
