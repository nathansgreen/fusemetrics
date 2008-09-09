PART 0: HOW TO BUILD
1) Create a property file, local.properties in the project root
2) Set jdk.home.1.5=C:/tools/Java/jdk1.5.0_10, to your local JDK
3) Type ant


PART 1: ANALYSIS

Scans directories for xml files to identify metric values. It then  activates the various
plugins with the appropriate information to do their work (specifically, to analyze the files and come up with metrics
score(s).  There are two kinds of analysis:
a) Single number - for example average code complexity
b) Histogram buckets - taking a large number of scores (say, for individual files) and sorting them into buckets.


PART 2: PERSISTENCE

Persist the metrics scores to a storage system

PART 3: RENDERING

Take metric scores and produce graphs:

a) For Single Number metrics - create a sparkline history of the change in the value over time
b) For histogram metrics - create a histogram report of the numbers

All of these graphs are saved to files for later viewing


PART 4: PRESENTATION

Produce an HTML page with the various metrics values and graphs (sparkline and histograms)







DESIGN CONCEPTS

1. A config file that describes which metrics are being requested, and for each metric:
   a) What plugin to use
   b) What directory tree to search through (defaults to a global directory if not defined)
   c) Optionals:
       i.  The regexp to use to identify files (there will be a default for each plugin)
       ii. The buckets & labels to use for histograms (will be a default here as well)

   d. Global settings in the configuration:
      i.   Top level directory for data                                  (default, the current working directory [cwd])
      ii.  Directory where persistence data is stored                    (default: the "dashboard" subdirectory of the cwd)
      iii. Directory where output .png and .html files are generated     (default: the "dashboard" subdirectory of the cwd)
      iv.  The template to use to create the output .html file           (default: dashboard.xslt)
      v.   The name of the output .html                                  (default: dashboard.html)


2. Metrics are "keys" - it's up to the user to determine which plugin to use to define "Lines of Code".  They could,
   in theory, use PMD violations as lines of code, if they mismatched the plugins.

3. Metrics have implied submetrics - some metrics, such as code coverage, can have multiple meaningful sub-values:
   line coverage, branch coverage.    The plugins should create the appropriate sub-metrics, with names which are
   deried from the top-level metric

   for example:    coverage  ->   coverage:branch and coverage:line

   In this example, there would be three metrics values - coverage, coverage:branch and coverage:line


4. Finding plugins
   Ideally, we'll search the code space for implementors of the MetricPlugin and HistogramPlugin interface, and add
   them to a master registry.

   If that's too hard, we'll have a file which lists the names of the plugins, and the classname of the implementation

   ametric : com.packagename.blah.AMetricPluginImplementation


5. Factories for the various sub-stages - we define an interface for persistence, for metric collection, for rendering
   and presentation, and allow others to create new implementations of these sub-elements.



LIKES/DISLIKES


Like:  This is flexible, and will support a lot of different metrics and rendering options
Like:  This is something that we can be proud of offering to the community
Like:  The various elements are separated, which makes the code easier to understand, maintain and improve.

Dislike: Complexity
Dislike: Flexibility begets confusion and user error
Dislike: Histograms represent a different type of metric, so we have to have a naming convention  "histo_loc"




SIMPLIFICATIONS:

1. Use an XML file for the initial configuration
2. Use an XML file for the plugin registry
3. Use an XML file for the "subfactory" registry, or just hard-code it for now.
\
