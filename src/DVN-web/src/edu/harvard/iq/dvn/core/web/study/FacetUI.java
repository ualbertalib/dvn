/*
 Copyright (C) 2005-2012, by the President and Fellows of Harvard College.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 Dataverse Network - A web application to share, preserve and analyze research data.
 Developed at the Institute for Quantitative Social Science, Harvard University.
 Version 3.0.
 */
package edu.harvard.iq.dvn.core.web.study;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FacetUI {

    private static final Logger logger = Logger.getLogger("edu.harvard.iq.dvn.core.web.study.FacetUI");

    String name;
    List<String> resultNames = new ArrayList<String>();
    List<String> resultHitCounts = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getResultNames() {
        return resultNames;
    }

    public void addResultNames(String resultName) {
        resultNames.add(resultName);
    }

    public List<String> getResultHits() {
        return resultHitCounts;
    }

    public void addResultHits(String resultHits) {
        resultHitCounts.add(resultHits);
    }

}
