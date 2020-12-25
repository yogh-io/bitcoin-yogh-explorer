# Regenerates the GWT client templates as quickly as possible (without having to do a GWT compile)
# Useful for when some client templates have not (yet) been generated or are outdated (such as when switching git branches)
# During development, this should not be needed because templates are regenerated as the templates are altered in the IDE (via the vue-gwt plugin).

mvn resources:resources compiler:compile -pl :bitcoin-explorer-client -am
