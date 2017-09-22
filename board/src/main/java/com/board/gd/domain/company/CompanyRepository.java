package com.board.gd.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by godong9 on 2017. 5. 14..
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByGroupMail(String groupMail);
    List<Company> findByCompanyMail(String companyMail);
}
