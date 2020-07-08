/*
 * The MIT License
 *
 * Copyright (c) 2010-2015 Jason Priem, Bruno P. Kinoshita
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

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * A parser capable of parsing name parts out of a single string.
 * </p>
 *
 * <p>
 * The code works by basically applying several Regexes in a certain order and
 * removing (chopping) tokens off the original string. The parser consumes the
 * tokens during its creation.
 * </p>
 *
 * <p>
 * This class is not thread-safe.
 * </p>
 *
 * @since 0.1
 */
public class HumanNameParserParser {

    /**
     * The input name object.
     */
    private final Name name;

    // other helpful values
    private List<String> salutations;
    private List<String> postnominals;
    private List<String> prefixes;
    private List<String> suffixes;

    // parsed values
    private String leadingInit;
    private String first;
    private String nicknames;
    private String middle;
    private String last;
    private String suffix;
    private String salutation;
    private String postnominal;
    
    HumanNameParserParser(final Name name,
            List<String> salutations,
            List<String> postnominals,
            List<String> prefixes,
            List<String> suffixes) {
        this.name = name;
        this.salutations = salutations;
        this.postnominals = postnominals;
        this.prefixes = prefixes;
        this.suffixes = suffixes;
    }

    /**
     * Gets the {@code Name} object.
     * 
     * @return the {@code Name} object
     */
    public Name getName() {
        return name;
    }

    // getters for parsed values

    public String getLeadingInit() {
        return leadingInit;
    }

    public String getFirst() {
        return first;
    }

    public String getNicknames() {
        return nicknames;
    }

    public String getMiddle() {
        return middle;
    }

    public String getLast() {
        return last;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getPostnominal() {
        return postnominal;
    }

    public String getSalutation() {
        return salutation;
    }

    /**
     * Consumes the string and creates the name parts.
     *
     * @throws ParseException
     *             if the parser fails to retrieve the name parts
     */
    void parse() throws ParseException {
        String suffixes = StringUtils.join(this.suffixes, "\\.*|") + "\\.*";
        String postnominals = StringUtils.join(this.postnominals, "\\.*|") + "\\.*";
        String salutations = StringUtils.join(this.salutations, "\\.*|") + "\\.*";
        String prefixes = StringUtils.join(this.prefixes, " |") + " ";

        // The regex use is a bit tricky. *Everything* matched by the regex will be
        // replaced,
        // but you can select a particular parenthesized submatch to be returned.
        // Also, note that each regex requires that the preceding ones have been run,
        // and matches chopped out.
        String nicknamesRegex = "(?i) ('|\\\"|\\(\\\"*'*)(.+?)('|\\\"|\\\"*'*\\)) "; // names that starts or end w/ an
                                                                                     // apostrophe break this
        String suffixRegex = "(?i)[,| ]+((" + suffixes + ")$)";
        String postnominalRegex = "(?i)[,| ]+((" + postnominals + ")$)";
        String lastRegex = "(?i)(?!^)\\b([^ ]+ y |" + prefixes + ")*[^ ]+$";
        String leadingInitRegex = "(?i)(^(.\\.*)(?= \\p{L}{2}))"; // note the lookahead, which isn't returned or
                                                                  // replaced
        String salutationsRegex = "(?i)^(" + salutations + "\\b)(\\.|\\s)+"; // salutation plus a word boundary \b
        String firstRegex = "(?i)^([^ ]+)";

        // get nickname, if there is one
        this.nicknames = this.name.chopWithRegex(nicknamesRegex, 2);

        // get postnominal, if there is one
        this.postnominal = this.name.chopWithRegex(postnominalRegex, 1);

        // get suffix, if there is one
        this.suffix = this.name.chopWithRegex(suffixRegex, 1);

        // flip the before-comma and after-comma parts of the name
        this.name.flip(",");

        // get the last name
        this.last = this.name.chopWithRegex(lastRegex, 0);
        if (StringUtils.isBlank(this.last)) {
            throw new ParseException("Couldn't find a last name in '{" + this.name.getStr() + "}'.");
        }

        // get salutation, if there is one
        this.salutation = this.name.chopWithRegex(salutationsRegex, 1);

        // get the first initial, if there is one
        this.leadingInit = this.name.chopWithRegex(leadingInitRegex, 1);

        // get the first name
        this.first = this.name.chopWithRegex(firstRegex, 0);
        if (StringUtils.isBlank(this.first)) {
            throw new ParseException("Couldn't find a first name in '{" + this.name.getStr() + "}'");
        }

        // if anything's left, that's the middle name
        this.middle = this.name.getStr();
    }

}
