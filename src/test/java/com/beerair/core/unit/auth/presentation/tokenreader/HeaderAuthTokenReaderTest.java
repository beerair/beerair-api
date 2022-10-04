package com.beerair.core.unit.auth.presentation.tokenreader;

import static com.beerair.core.auth.presentation.tokenreader.HeaderAuthTokenReader.TOKEN_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.beerair.core.auth.presentation.tokenreader.HeaderAuthTokenReader;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HeaderAuthTokenReaderTest {
    private HeaderAuthTokenReader reader;
    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        reader = new HeaderAuthTokenReader();
    }

    @DisplayName("Header에서 Accesss Token을 가져온다.")
    @Test
    void read() {
        final String EXPERT = "1234";
        stubbingByGetHeader(EXPERT);

        var actual = reader.read(httpServletRequest);
        assertThat(actual.get()).isEqualTo(EXPERT);
    }

    private void stubbingByGetHeader(String expert) {
        when(httpServletRequest.getHeader("authorization"))
                .thenReturn(TOKEN_TYPE + " " + expert);
    }
}
