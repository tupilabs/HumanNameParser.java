/*
 * The MIT License
 *
 * Copyright (c) 2010-2025 Jason Priem, Bruno P. Kinoshita
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tupilabs.human_name_parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@code Name} and {@code HumanNameParserParser}. Utilizes the same
 * input file as the PHP library 0.2 version.
 *
 * @since 0.1
 */
public class NameTest {

    protected Name object;

    @BeforeEach
    public void setUp() {
        object = new Name("Bjorn O'Malley");
    }

    @Test
    public void testSetStrRemovesWhitespaceAtEnds() {
        object.setStr("    Bjorn O'Malley \r\n");
        assertEquals(
            "Bjorn O'Malley",
            object.getStr()
        );
    }

    @Test
    public void testSetStrRemovesRedudentantWhitespace(){
        object.setStr(" Bjorn    O'Malley");
        assertEquals(
            "Bjorn O'Malley",
            object.getStr()
        );
    }

    @Test
    public void testChopWithRegexReturnsChoppedSubstring(){
        object.setStr("Bjorn O'Malley");
        assertEquals(
            "Bjorn",
            object.chopWithRegex("(^([^ ]+))(.+)", 1)
        );
    }

    @Test
    public void testChopWithRegexChopsStartOffNameStr(){
        object.setStr("Bjorn O'Malley");
        object.chopWithRegex("(^[^ ]+)", 0);
        assertEquals(
                "O'Malley",
            object.getStr()
        );
    }

    @Test
    public void testChopWithRegexChopsEndOffNameStr(){
        object.setStr("Bjorn O'Malley");
        object.chopWithRegex("( (.+)$)", 1);
        assertEquals(
            "Bjorn",
            object.getStr()
        );
    }

    @Test
    public void testChopWithRegexChopsMiddleFromNameStr(){
        object.setStr("Bjorn 'Bill' O'Malley");
        object.chopWithRegex("( '[^']+' )", 0);
        assertEquals(
            "Bjorn O'Malley",
            object.getStr()
        );
    }

    @Test
    public void testFlip() {
        object.setStr("O'Malley, Bjorn");
        object.flip(",");
        assertEquals(
            "Bjorn O'Malley",
            object.getStr()
        );
    }

}
