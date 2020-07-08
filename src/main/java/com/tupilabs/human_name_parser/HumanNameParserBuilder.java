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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A builder to construct {@code HumanNameParserParser}.
 * @since 0.2
 * @see HumanNameParserParser
 */
public final class HumanNameParserBuilder {

    // constants with default values
    public static final List<String> DEFAULT_SALUTATIONS = Collections.unmodifiableList(
            Arrays.asList(
                    "mr",
                    "master",
                    "mister",
                    "mrs",
                    "miss",
                    "ms",
                    "dr",
                    "prof",
                    "rev",
                    "fr",
                    "judge",
                    "honorable",
                    "hon"));
    public static final List<String> DEFAULT_POSTNOMINALS = Collections.unmodifiableList(
            Arrays.asList(
                    "phd",
                    "ph.d.",
                    "ph.d",
                    "esq",
                    "esquire",
                    "apr",
                    "rph",
                    "pe",
                    "md",
                    "ma",
                    "dmd",
                    "cme",
                    "dds",
                    "cpa",
                    "dvm"));
    public static final List<String> DEFAULT_PREFIXES = Collections.unmodifiableList(
            Arrays.asList(
                    "bar",
                    "ben",
                    "bin",
                    "da",
                    "dal",
                    "de la",
                    "de",
                    "del",
                    "der",
                    "di",
                    "ibn",
                    "la",
                    "le",
                    "san",
                    "st",
                    "ste",
                    "van",
                    "van der",
                    "van den",
                    "vel",
                    "von"));
    public static final List<String> DEFAULT_SUFFIXES = Collections.unmodifiableList(
            Arrays.asList(
                    "jr",
                    "sr",
                    "2",
                    "ii",
                    "iii",
                    "iv",
                    "v",
                    "senior",
                    "junior"));

    // build values
    private Name name;
    private List<String> salutations;
    private List<String> postnominals;
    private List<String> prefixes;
    private List<String> suffixes;

    /**
     * Create the parser builder for a name.
     * @param name the name
     */
    public HumanNameParserBuilder(String name) {
        super();
        Objects.requireNonNull(name);
        this.name = new Name(name);
    }

    /**
     * Create the parser builder for a name.
     * @param name the name
     */
    public HumanNameParserBuilder(Name name) {
        super();
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Build the parser.
     * @return a {@code HumanNameParserParser}
     */
    public HumanNameParserParser build() {
        if (this.salutations == null) {
            this.salutations = DEFAULT_SALUTATIONS;
        }
        if (this.postnominals == null) {
            this.postnominals = DEFAULT_POSTNOMINALS;
        }
        if (this.prefixes == null) {
            this.prefixes = DEFAULT_PREFIXES;
        }
        if (this.suffixes == null) {
            this.suffixes = DEFAULT_SUFFIXES;
        }
        final HumanNameParserParser parser = new HumanNameParserParser(
            name,
            salutations,
            postnominals,
            prefixes,
            suffixes
        );
        parser.parse();
        return parser;
    }

    // salutations

    public HumanNameParserBuilder withSalutations(List<String> salutations) {
        Objects.requireNonNull(salutations);
        this.salutations = salutations;
        return this;
    }

    public HumanNameParserBuilder withExtraSalutations(List<String> salutations) {
        Objects.requireNonNull(salutations);
        this.salutations = new ArrayList<>(salutations);
        this.salutations.addAll(DEFAULT_SALUTATIONS);
        return this;
    }

    // postnominals

    public HumanNameParserBuilder withPostnominals(List<String> postnominals) {
        Objects.requireNonNull(postnominals);
        this.postnominals = postnominals;
        return this;
    }

    public HumanNameParserBuilder withExtraPostnominals(List<String> postnominals) {
        Objects.requireNonNull(postnominals);
        this.postnominals = new ArrayList<>(postnominals);
        this.postnominals.addAll(DEFAULT_POSTNOMINALS);
        return this;
    }

    // prefixes
    
    public HumanNameParserBuilder withPrefixes(List<String> prefixes) {
        Objects.requireNonNull(prefixes);
        this.prefixes = prefixes;
        return this;
    }

    public HumanNameParserBuilder withExtraPrefixes(List<String> prefixes) {
        Objects.requireNonNull(prefixes);
        this.prefixes = new ArrayList<>(prefixes);
        this.prefixes.addAll(DEFAULT_PREFIXES);
        return this;
    }

    // suffixes
    
    public HumanNameParserBuilder withSuffixes(List<String> suffixes) {
        Objects.requireNonNull(suffixes);
        this.suffixes = suffixes;
        return this;
    }

    public HumanNameParserBuilder withExtraSuffixes(List<String> suffixes) {
        Objects.requireNonNull(suffixes);
        this.suffixes = new ArrayList<>(suffixes);
        this.suffixes.addAll(DEFAULT_SUFFIXES);
        return this;
    }
}
