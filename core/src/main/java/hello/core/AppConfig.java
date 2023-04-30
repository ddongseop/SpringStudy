package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { //역할에 맞는 구현을 선택해주는 공연기획자 (구현 객체를 생성하고 연결)

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //[DI] Dependency Injection
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); //JdbcMemberRepository로 변경할 때 이 부분만 변경하면됨
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
       // return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //[OCP] 확장에는 열려있고, 변경에는 닫혀있도록 잘 구현됨
    }

}
