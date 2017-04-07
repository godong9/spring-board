package com.board.gd.user;

import lombok.*;

import javax.persistence.*;

/**
 * Created by gd.godong9 on 2017. 4. 7.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "user_id")
    private Long userId;
}

