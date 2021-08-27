package com.example.loan.snippet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static capital.scalable.restdocs.section.SectionBuilder.DEFAULT_SNIPPETS;

public class SectionBuilder {

    public final Collection<String> defaultSnippets = new ArrayList<>(DEFAULT_SNIPPETS);

    private boolean skipEmpty = false;

    public SectionBuilder() {
    }

    public SectionBuilder addSnippetNames(List<String> snippetNames) {
        this.defaultSnippets.addAll(snippetNames);
        return this;
    }

    public SectionBuilder addSnippetNames(String... snippetNames) {
        this.defaultSnippets.addAll(List.of(snippetNames));
        return this;
    }

    /**
     * If snippet would not generate any output (e.g. endpoint has no path parameters),
     * than this snippet would not be included in the section.
     *
     * @param skipEmpty true if snippets with no content should not be included in the section;
     *                  false otherwise (default)
     * @return this
     */
    public SectionBuilder skipEmpty(boolean skipEmpty) {
        this.skipEmpty = skipEmpty;
        return this;
    }

    public SectionSnippet build() {
        return new SectionSnippet(defaultSnippets, skipEmpty);
    }
}
