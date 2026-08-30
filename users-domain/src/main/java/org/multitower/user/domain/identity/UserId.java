package org.multitower.user.domain.identity;

import lombok.Value;


@Value
public class UserId {
    long id;

    public static UserId of(long id) {
        return new UserId(id);
    }
}
