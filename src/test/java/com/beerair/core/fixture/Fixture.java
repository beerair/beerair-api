package com.beerair.core.fixture;

import com.beerair.core.error.TestDebugException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;

/**
 * @author 김재원
 *
 * 테스트 객체 구성을 도와주는 Reflection Util 입니다.
 * */
public class Fixture<T> {
    private final Object instance;

    public Fixture(Object instance) {
        this.instance = instance;
    }

    /**
     * 필드의 값을 설정 합니다.
     * 탐색에 실패하면 부모 객체를 탐색해 설정 합니다.
     *
     * @param fieldName 설정할 필드의 이름
     * @param fieldValue 설정할 필드의 값
     * @return this
     *
     * @exception TestDebugException 모든 부모 객체에서도 지정된 이름의 필드를 찾지 못했을경우
     */
    public Fixture<T> set(String fieldName, Object fieldValue) {
        try {
            Field field = getField(instance.getClass(), fieldName);
            field.setAccessible(true);
            field.set(instance, fieldValue);
            return this;
        } catch (IllegalAccessException e) {
            throw new TestDebugException(e);
        }
    }

    /**
     * {@link Fixture#set(String, Object)}
     *
     * 필드의 값을 Null로 설정 합니다.
     */
    public Fixture<T> setNull(String fieldName) {
        return set(fieldName, null);
    }

    /**
     * 필드의 값을 반환 합니다.
     * 탐색에 실패하면 부모 객체를 탐색해 반환 합니다.
     *
     * @param fieldName 가져올 필드의 이름
     * @return this
     *
     * @exception TestDebugException 모든 부모 객체에서도 지정된 이름의 필드를 찾지 못했을경우
     */
    private Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClazz = clazz.getSuperclass();
            if (Objects.isNull(superClazz)) {
                throw new TestDebugException(e);
            }
            return getField(superClazz, fieldName);
        }
    }

    @SneakyThrows
    public <T> T getField(String fieldName) {
        return (T) getField(instance.getClass(), fieldName).get(instance);
    }

    /**
     * 지정된 이름의 메서드를 실행 합니다.
     * 탐색에 실패하면 부모 객체를 탐색해 실행 합니다.
     *
     * @param methodName 실행할 메서드 이름
     * @param paramTypes 파라메터 타입
     * @param params 실제 파라메터
     * @return 메서드 반환 값
     *
     * @exception TestDebugException 모든 부모 객체에서도 지정된 이름의 메서드를 찾지 못했을경우
     */
    @SuppressWarnings("unchecked")
    public <T> T exec(String methodName, List<Class<?>> paramTypes, Object... params) {
        try {
            Method method = getMethod(instance.getClass(), methodName, paramTypes.toArray(new Class<?>[0]));
            method.setAccessible(true);
            return (T) method.invoke(params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new TestDebugException(e);
        }
    }

    private Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        try {
            return clazz.getDeclaredMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClazz = clazz.getSuperclass();
            if (Objects.isNull(superClazz)) {
                throw new TestDebugException(e);
            }
            return getMethod(superClazz, methodName);
        }
    }

    /**
     * {@link Fixture#exec(String, List, Object...)}
     *
     * 지정된 이름의 파라메터가 없는 메서드를 실행 합니다.
     */
    public <T> T exec(String methodName) {
        return exec(methodName, Collections.emptyList());
    }

    /**
     * 인스턴스를 반환 합니다.
     * @return 인스턴스
     */
    @SuppressWarnings("unchecked")
    public T get() {
        return (T) instance;
    }
}
