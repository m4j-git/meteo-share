/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2018, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package ru.m4j.meteo.share.thmlf;

import java.io.IOException;
import java.io.Writer;

/**
 * <p>
 *   Common interface for CharSequences that can be directly written to an output {@link Writer}.
 * </p>
 * <p>
 *   This will be used by a variety of implementations providing the capability to write text generated by
 *   the engine somehow directly to output (to the output writer, which will correspond to an
 *   {@code HttpServletResponse#getWriter()} writer in most web applications). This avoids the need to generate
 *   a large number of (possibly large) {@code String} object in memory before these values being output.
 * </p>
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public interface IWritableCharSequence extends CharSequence {

    /**
    * <p>
    *   Write the contents of this char sequence directly to an output {@link Writer}.
    * </p>
    * <p>
    *   This method can avoid the need to create a {@link String} object containing all the contents in
    *   this character sequence just when we want to write it to a {@link Writer}.
    * </p>
    *
    * @param writer the writer to write the character sequence to.
    * @throws IOException if an input/output exception happens during writing
    */
    void write(Writer writer) throws IOException;

}
