package com.example.petarrange.aop;

import com.example.petarrange.annotation.EncryptField;
import com.example.petarrange.annotation.EncryptMethod;
import com.example.petarrange.utils.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.function.UnaryOperator;

@Slf4j
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public class EncryptMethodAspect {

    @Around("@annotation(encryptMethod)")
    public Object around(ProceedingJoinPoint joinPoint, EncryptMethod encryptMethod) throws Throwable{
        try {

            int[] indexArr=encryptMethod.value();
            Object[] args=joinPoint.getArgs();

            boolean enableDecrypt = encryptMethod.enabledDecrypt();
            // 加密指定位置参数
            for (int i : indexArr) {
                Object arg = args[i];

                // 只有 String 类型才加密
                if (arg instanceof String) {
                    args[i] = AESUtils.encrypt((String) arg);
                }
            }



            return joinPoint.proceed(args);

//            for(int i=0;i<=indexArr.length;i++){
//                if(i>= args.length){
//                    break;
//                }
//                System.out.println(args[i]);
//                handleEncrypt(args[i]);
//                System.out.println(args[i]);
//
//            }
//            Object result=joinPoint.proceed();
//            if(encryptMethod.enabledDecrypt()){
//                return handleDecrypt(result);
//            }
//            return result;
        }catch (Throwable throwable){
            throw throwable;
        }
    }

    private void handleEncrypt(Object obj) throws IllegalAccessException {
        if (obj == null ) {
            return;
        }
        System.out.println("encrypting");
        handleEnDecrypt(obj, AESUtils::encrypt);
    }

    /**
     * 对添加了 EncryptField 注解的字段进行解密, <b>只考虑了返回值是对象的情况</b>
     *
     * @param obj 要处理的对象
     * @return 结果
     */
    private Object handleDecrypt(Object obj) throws IllegalAccessException {
        return handleEnDecrypt(obj, AESUtils::decrypt);
    }

//    private Object handleEncrypt(Object obj, UnaryOperator<String> handleFun) throws IllegalAccessError, IllegalAccessException {
//        if (obj == null) {
//            return null;
//        }
//
//        if (obj instanceof String) {
//            return handleFun.apply((String) obj);  // 对字符串直接进行加密/解密
//        }
//
//        // 对对象的字段进行加解密
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
//            if (hasSecureField) {
//                field.setAccessible(true);
//                String val = (String) field.get(obj);
//                String result = handleFun.apply(val);  // 调用 AESUtils::decrypt
//                field.set(obj, result);
//            }
//        }
//        return obj;
//    }

    private Object handleEnDecrypt(Object obj, UnaryOperator<String> handleFun) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }


        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println("当前处理的对象：" + obj.getClass().getName());


        for (Field field : fields) {
            System.out.println("encrypting4");
            if (field.isAnnotationPresent(EncryptField.class)) {

            field.setAccessible(true);
            System.out.println("additionnal");
            String val = (String) field.get(obj);
            System.out.println("5");
            String result = handleFun.apply(val);
            System.out.println("6");
            System.out.println(val+"aaaa"+result);
            System.out.println("7");
            field.set(obj, result);
            System.out.println("8");
            }

        }
        System.out.println(3);
        return obj;
    }

}
