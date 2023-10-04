package com.totterman.mvnspringboot3;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoreDomainTest {
    @Test
    void newVideoEntityShouldHaveNullId() {
        VideoEntity entity = new VideoEntity("title", "description", "alice");
        assertThat(entity.getId()).isNull();
        assertThat(entity.getUsername()).isEqualTo("alice");
        assertThat(entity.getName()).isEqualTo("title");
        assertThat(entity.getDescription()).isEqualTo("description");
    }
    @Test
    void toStringShouldAlsoBeTested() {
        VideoEntity entity = new VideoEntity("title", "description", "alice");
        assertThat(entity.toString()).isEqualTo("VideoEntity [id=null, name=title, description=description, username=alice]");
    }
    @Test
    void settersShouldMutateState() {
        VideoEntity entity = new VideoEntity("title", "description", "alice");
        entity.setId(99L);
        entity.setName("new name");
        entity.setDescription("new desc");
        entity.setUsername("bob");
        assertThat(entity.getId()).isEqualTo(99L);
        assertThat(entity.getName()).isEqualTo("new name");
        assertThat(entity.getDescription()).isEqualTo("new desc");
        assertThat(entity.getUsername()).isEqualTo("bob");
    }
}
