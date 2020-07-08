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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for the {@code HumanNameParserBuilder}.
 * @author kinow
 *
 */
public class BuilderTest {

    // suffixes

    @Test
    public void testDefaultSuffixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.suffixes.contains("senior"));
        assertFalse(parser.suffixes.contains("mage"));
    }

    @Test
    public void testReplacingSuffixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withSuffixes(Arrays.asList("mage")).build();
        assertFalse(parser.suffixes.contains("senior"));
        assertTrue(parser.suffixes.contains("mage"));
    }

    @Test
    public void testExtraSuffixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withExtraSuffixes(Arrays.asList("mage")).build();
        assertTrue(parser.suffixes.contains("senior"));
        assertTrue(parser.suffixes.contains("mage"));
    }

    // prefixes

    @Test
    public void testDefaultPrefixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.prefixes.contains("de la"));
    }

    @Test
    public void testReplacingPrefixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withPrefixes(Arrays.asList("sama")).build();
        assertFalse(parser.prefixes.contains("de la"));
        assertTrue(parser.prefixes.contains("sama"));
    }

    @Test
    public void testExtraPrefixes() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withExtraPrefixes(Arrays.asList("sama")).build();
        assertTrue(parser.prefixes.contains("de la"));
        assertTrue(parser.prefixes.contains("sama"));
    }

    // postnominals

    @Test
    public void testDefaultPostnominals() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.postnominals.contains("phd"));
    }

    @Test
    public void testReplacingPostnominals() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        // Au.D. is one of the examples from issue #10 on GitHub
        HumanNameParserParser parser = builder.withPostnominals(Arrays.asList("Au.D.")).build();
        assertFalse(parser.postnominals.contains("phd"));
        assertTrue(parser.postnominals.contains("Au.D."));
    }

    @Test
    public void testExtraPostnominals() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withExtraPostnominals(Arrays.asList("Au.D.")).build();
        assertTrue(parser.postnominals.contains("phd"));
        assertTrue(parser.postnominals.contains("Au.D."));
    }

    // salutations

    @Test
    public void testDefaultSalutations() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.build();
        assertTrue(parser.salutations.contains("judge"));
    }

    @Test
    public void testReplacingSalutations() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withSalutations(Arrays.asList("sinho")).build();
        assertFalse(parser.salutations.contains("judge"));
        assertTrue(parser.salutations.contains("sinho"));
    }

    @Test
    public void testExtraSalutations() {
        HumanNameParserBuilder builder = new HumanNameParserBuilder("john");
        HumanNameParserParser parser = builder.withExtraSalutations(Arrays.asList("sinho")).build();
        assertTrue(parser.salutations.contains("judge"));
        assertTrue(parser.salutations.contains("sinho"));
    }

    // validations

    @Test(expected = NullPointerException.class)
    public void testConstructorFailsWithNullString() {
        new HumanNameParserBuilder((String) null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorFailsWithNullName() {
        new HumanNameParserBuilder((Name) null);
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullSalutations1() {
        new HumanNameParserBuilder("john").withSalutations(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullSalutations2() {
        new HumanNameParserBuilder("john").withExtraSalutations(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullPostnominals1() {
        new HumanNameParserBuilder("john").withPostnominals(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullPostnominals2() {
        new HumanNameParserBuilder("john").withExtraPostnominals(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullSuffixes1() {
        new HumanNameParserBuilder("john").withSuffixes(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullSuffixes2() {
        new HumanNameParserBuilder("john").withExtraSuffixes(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullPrefixes1() {
        new HumanNameParserBuilder("john").withPrefixes(null).build();
    }

    @Test(expected = NullPointerException.class)
    public void testFailsToBuildWithNullPrefixes2() {
        new HumanNameParserBuilder("john").withExtraPrefixes(null).build();
    }

    @Test
    public void testCreateWithStringOrName() {
        assertEquals("ramon", new HumanNameParserBuilder("don ramon valdez").withSalutations(Arrays.asList("don")).build().getFirst());
        assertEquals("ramon", new HumanNameParserBuilder(new Name("don ramon valdez")).withSalutations(Arrays.asList("don")).build().getFirst());
    }
}
