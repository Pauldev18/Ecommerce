package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Customer;
import com.ecommerce.Ecomerce.Repository.CustomerRepository;
import com.ecommerce.Ecomerce.Service.CustomerService;
import com.ecommerce.Ecomerce.Dto.AuthRequest;
import com.ecommerce.Ecomerce.Dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    // Nếu chưa cấu hình bean, bạn có thể new trực tiếp:
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @Override
    public Customer getById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy Customer với ID: " + id));
    }

    @Override
    public AuthResponse register(Customer customer) {
        repo.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new IllegalArgumentException("Email đã được sử dụng");
        });
        // mã hóa mật khẩu
        customer.setPasswordHash(encoder.encode(customer.getPasswordHash()));
        customer.setRegisteredAt(new Date());
        customer.setUpdatedAt(new Date());
        Customer saved = repo.save(customer);
        return new AuthResponse("Đăng ký thành công", saved.getId());
    }

    @Override
    public AuthResponse login(AuthRequest req) {
        Customer c = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Email không tồn tại"));
        if (!encoder.matches(req.getPassword(), c.getPasswordHash())) {
            throw new IllegalArgumentException("Sai mật khẩu");
        }
        return new AuthResponse("Đăng nhập thành công", c.getId());
    }

    @Override
    public Customer getProfile(UUID id) {
        return getById(id);
    }

    @Override
    public Customer update(UUID id, Customer newC) {
        Customer existing = getById(id);
        existing.setFirstName(newC.getFirstName());
        existing.setLastName(newC.getLastName());
        existing.setActive(newC.isActive());
        existing.setUpdatedAt(new Date());
        return repo.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
