package com.rafael0117.SpringBoot_react.repository;

import com.rafael0117.SpringBoot_react.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
