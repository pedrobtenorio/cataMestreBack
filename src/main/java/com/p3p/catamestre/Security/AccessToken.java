package com.p3p.catamestre.Security;

import lombok.*;


@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class AccessToken {

    @NonNull
    private String accessToken;

}