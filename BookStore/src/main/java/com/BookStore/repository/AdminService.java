package com.BookStore.repository;

import com.BookStore.dto.*;
import com.BookStore.entity.Admin;
import org.springframework.data.domain.Page;


public interface AdminService {
    void registerAdmin(AdminRegisterRequest request);
    Admin login(AdminLoginRequest request);

    String recoverAccount(AdminRecoveryRequest request);
    void updatePassword(AdminUpdatePasswordRequest request);

    void updateInfo(AdminUpdateInfoRequest request);

    void deleteAccount(AdminDeleteAccountRequest request);
    Page<Admin> queryAdmins(AdminQueryRequest request);

    void resetUserPassword(AdminResetUserPasswordRequest request);

    void freezeUserAccount(AdminFreezeUserRequest request);

    void unfreezeUserAccount(AdminUnfreezeUserRequest request);

}
