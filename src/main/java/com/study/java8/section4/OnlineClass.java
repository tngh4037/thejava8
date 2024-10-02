package com.study.java8.section4;

import java.util.Optional;
import java.util.OptionalInt;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress); // ofNullable: Optional 안에 넣는 값이 null일수 있다. | of: Optional 안에 넣는 값은 무조건 null이 아니어야 한다. (null이 들어오면 NPE가 발생한다.)
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    // Optioanl 은 리턴타입으로만 사용하자.
    // - Optional 자체가 null일 수 있다. ( 메서드 호출시 인자로 null이 전달될 수 있다. ex. setProgress(null); ) 따라서 Optional 자체가 null인지를 체크하는 로직이 필요하다. => Optional 을 쓰는 의미가 없다. 오히려 더 번거로워 진다.
    // - 내부 로직에서 Optional 내부 값이 비어있는지 체크해야 한다.
    // public void setProgress(Optional<Progress> progress) {
    //     if (progress.isPresent()) {
    //         progress.ifPresent(p -> {
    //             this.progress = p;
    //         });
    //     }
    // }
}
