package edu.stanford.bmir.protege.web.server.shortform;

import com.google.common.collect.ImmutableMap;
import edu.stanford.bmir.protege.web.shared.pagination.Page;
import edu.stanford.bmir.protege.web.shared.pagination.PageRequest;
import edu.stanford.bmir.protege.web.shared.shortform.DictionaryLanguage;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-07-08
 */
public class MultiLingualDictionaryLuceneImpl implements MultiLingualDictionary {

    @Nonnull
    private final MultiLingualShortFormDictionary multiLingualShortFormDictionary;

    @Nonnull
    private final SearchableMultiLingualShortFormDictionary searchableMultiLingualShortFormDictionary;


    @Inject
    public MultiLingualDictionaryLuceneImpl(@Nonnull MultiLingualShortFormDictionary multiLingualShortFormDictionary,
                                            @Nonnull SearchableMultiLingualShortFormDictionary searchableMultiLingualShortFormDictionary) {
        this.multiLingualShortFormDictionary = checkNotNull(multiLingualShortFormDictionary);
        this.searchableMultiLingualShortFormDictionary = checkNotNull(searchableMultiLingualShortFormDictionary);
    }

    @Nonnull
    @Override
    public String getShortForm(@Nonnull OWLEntity entity,
                               @Nonnull List<DictionaryLanguage> languages,
                               @Nonnull String defaultShortForm) {
        return multiLingualShortFormDictionary.getShortForm(entity, languages, defaultShortForm);
    }

    @Nonnull
    @Override
    public ImmutableMap<DictionaryLanguage, String> getShortForms(@Nonnull OWLEntity entity,
                                                                  @Nonnull List<DictionaryLanguage> languages) {
        return multiLingualShortFormDictionary.getShortForms(entity, languages);
    }

    @Nonnull
    @Override
    public Stream<OWLEntity> getEntities(@Nonnull String shortForm, @Nonnull List<DictionaryLanguage> languages) {
        throw new RuntimeException("Not Implemented");
    }

    @Nonnull
    @Override
    public Page<EntityShortFormMatches> getShortFormsContaining(@Nonnull List<SearchString> searchStrings,
                                                        @Nonnull Set<EntityType<?>> entityTypes,
                                                        @Nonnull List<DictionaryLanguage> languages,
                                                        @Nonnull PageRequest pageRequest) {
        return searchableMultiLingualShortFormDictionary.getShortFormsContaining(searchStrings,
                                                                                 entityTypes,
                                                                                 languages,
                                                                                 pageRequest);
    }
}

