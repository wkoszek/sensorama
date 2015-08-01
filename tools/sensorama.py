#!/usr/bin/env python

import sys
import json

def usage():
    print "validate.py <jsonfilename>\n"
    sys.exit(1)

def main():
    if len(sys.argv) != 2:
        usage()

    fn = sys.argv[1]
    print "# fn=" + fn

    with open(fn, "r") as f:
        js = json.load(f)
    f.close()

    js = json.dumps(js, sort_keys=True, indent=4, separators=(',', ': '))

    print js

if __name__ == "__main__":
    main();

