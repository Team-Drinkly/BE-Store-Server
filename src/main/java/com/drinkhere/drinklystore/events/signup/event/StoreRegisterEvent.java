//package com.drinkhere.drinklymember.events.signup.event;
//
//public record MemberSignUpEvent(
//        Long memberId,
//        String memberName
//) {
//}
package com.drinkhere.drinklystore.events.signup.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StoreRegisterEvent extends ApplicationEvent {
    private final String type;
    private final Long order;
    private final String name;
    private final String mobileNo;

    public StoreRegisterEvent(Object source, String type, Long order, String name, String mobileNo) {
        super(source);
        this.type = type;
        this.order = order;
        this.name = name;
        this.mobileNo = mobileNo;
    }
}