package com.board.gd.domain.company;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "companies")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "group_mail")
    private String groupMail;

    @Column(name = "company_mail")
    private String companyMail;

    @Column(name = "homepage")
    private String homepage;
}
