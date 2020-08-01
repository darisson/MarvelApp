package com.android.github.marvelapp.marvelapp.domain.mapper;

import java.util.Collection;

public interface ModelMapper<From, To> {

    To transform(From from);

    Collection<To> transformCollection(Collection<From> fromList);
}
