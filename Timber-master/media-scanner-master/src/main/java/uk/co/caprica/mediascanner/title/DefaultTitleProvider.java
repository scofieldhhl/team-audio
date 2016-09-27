/*
 * This file is part of media-scanner.
 *
 * media-scanner is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * media-scanner is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with media-scanner.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2014 Caprica Software Limited.
 */

package uk.co.caprica.mediascanner.title;

import com.google.common.io.Files;

import java.nio.file.Path;

import uk.co.caprica.mediascanner.domain.MediaTitle;

/**
 *
 */
public final class DefaultTitleProvider implements TitleProvider {

    @Override
    public MediaTitle getTitle(Path file) {
        return new MediaTitle(Files.getNameWithoutExtension(file.toString()));
    }
}