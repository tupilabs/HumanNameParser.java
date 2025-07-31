/*
 * The MIT License
 *
 * Copyright (c) 2010-2020 Jason Priem, Bruno P. Kinoshita
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@code HumanNameParserBuilder}.
 * @author kinow
 *
 */
public class BuilderTest {

    // suffixes

    @Test
    public void testDefaultSuffixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.suffixes.contains("senior"));
        assertFalse(parser.suffixes.contains("mage"));
    }

    @Test
    public void testReplacingSuffixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withSuffixes(Arrays.asList("mage")).build();
        assertFalse(parser.suffixes.contains("senior"));
        assertTrue(parser.suffixes.contains("mage"));
    }

    @Test
    public void testExtraSuffixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withExtraSuffixes(Arrays.asList("mage")).build();
        assertTrue(parser.suffixes.contains("senior"));
        assertTrue(parser.suffixes.contains("mage"));
    }

    // prefixes

    @Test
    public void testDefaultPrefixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.prefixes.contains("de la"));
    }

    @Test
    public void testReplacingPrefixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withPrefixes(Arrays.asList("sama")).build();
        assertFalse(parser.prefixes.contains("de la"));
        assertTrue(parser.prefixes.contains("sama"));
    }

    @Test
    public void testExtraPrefixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withExtraPrefixes(Arrays.asList("sama")).build();
        assertTrue(parser.prefixes.contains("de la"));
        assertTrue(parser.prefixes.contains("sama"));
    }

    // postnominals

    @Test
    public void testDefaultPostnominals() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.postnominals.contains("phd"));
    }

    @Test
    public void testReplacingPostnominals() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        // Au.D. is one of the examples from issue #10 on GitHub
        HumanNameParserParser parser = builder.withPostnominals(Arrays.asList("Au.D.")).build();
        assertFalse(parser.postnominals.contains("phd"));
        assertTrue(parser.postnominals.contains("Au.D."));
    }

    @Test
    public void testExtraPostnominals() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withExtraPostnominals(Arrays.asList("Au.D.")).build();
        assertTrue(parser.postnominals.contains("phd"));
        assertTrue(parser.postnominals.contains("Au.D."));
    }

    // salutations

    @Test
    public void testDefaultSalutations() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.salutations.contains("judge"));
    }

    @Test
    public void testReplacingSalutations() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withSalutations(Arrays.asList("sinho")).build();
        assertFalse(parser.salutations.contains("judge"));
        assertTrue(parser.salutations.contains("sinho"));
    }

    @Test
    public void testExtraSalutations() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john paul");
        HumanNameParserParser parser = builder.withExtraSalutations(Arrays.asList("sinho")).build();
        assertTrue(parser.salutations.contains("judge"));
        assertTrue(parser.salutations.contains("sinho"));
    }

    @Test
    public void testLastNameNotMistakenForPostnominal() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("ruvin phidd");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.getFirst().contains("ruvin"));
        assertTrue(parser.getLast().contains("phidd"));
    }

    // validations

    @Test
    public void testConstructorFailsWithNullString() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder((String) null));
    }

    @Test
    public void testConstructorFailsWithNullName() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder((Name) null));
    }

    @Test
    public void testFailsToBuildWithNullSalutations1() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withSalutations(null).build());
    }

    @Test
    public void testFailsToBuildWithNullSalutations2() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withExtraSalutations(null).build());
    }

    @Test
    public void testFailsToBuildWithNullPostnominals1() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withPostnominals(null).build());
    }

    @Test
    public void testFailsToBuildWithNullPostnominals2() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withExtraPostnominals(null).build());
    }

    @Test
    public void testFailsToBuildWithNullSuffixes1() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withSuffixes(null).build());
    }

    @Test
    public void testFailsToBuildWithNullSuffixes2() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withExtraSuffixes(null).build());
    }

    @Test
    public void testFailsToBuildWithNullPrefixes1() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withPrefixes(null).build());
    }

    @Test
    public void testFailsToBuildWithNullPrefixes2() {
        assertThrows(NullPointerException.class, () -> new HumanNameParserBuilder("john paul").withExtraPrefixes(null).build());
    }

    @Test
    public void testCreateWithStringOrName() {
        assertEquals("ramon", new HumanNameParserBuilder("don ramon valdez").withSalutations(Arrays.asList("don")).build().getFirst());
        assertEquals("ramon", new HumanNameParserBuilder(new Name("don ramon valdez")).withSalutations(Arrays.asList("don")).build().getFirst());
    }
}
