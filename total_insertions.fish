#!/usr/bin/env fish

# Check wether both author and path are present
if test (count $argv) -eq 2
	set repo_path $argv[2]
	set author_name $argv[1]
# When args are 1, it means it is specified only the author,
# So retrieve author from the arg while repo is set to current folder
#Note that current folder must be a git repo
else if test (count $argv) -eq 1
	echo "Missing repo (E.g → Usage $0 <author> <repo-path>)"
	echo Starting with default repo → (pwd)
	set author_name $argv[1]
	set repo_path (pwd)
# No args are passed setting all to default: current repo while for the author section
# all contributors are mentioned
else
	echo Starting with default repo → (pwd)
	echo "No author mentions(E.g → Usage $0 <author> <repo-path>)"
	echo Using all authors
	set repo_path (pwd)
	cd $repo_path
	set author_name (git log --format='%aN' | sort -u)
end

#Check if repo passed does exist
if not test -d $repo_path
	echo "Directory does not exists $repo_path"
	exit 2
end

#Move to the repo for git log <...> to work
cd $repo_path

#Function for summing all stats by format $1 means insertions while $2 means deletions
function stats 
	git log --author="$argv[1]" --pretty=tformat: --numstat | awk '{ sum += $argv[2] } END { print sum }'
end

#Function for printing stats: insertions and deletions
#Single param is needed, representing the author
function print_stats
	set total_insert (stats $argv[1] $1)
	set total_dels (stats $argv[1] $2)
	
	#Check whether any errors occured, meaning no stats available
	if test -z "$total_insert" 
		set total_insert 0
	end
	if test -z "$total_dels"
		set total_dels 0
	end
	#Print stats by author
	echo "Author → '$argv[1]' has insertions: $total_insert, deletions: $total_dels"
end

#Cycle to every author (if multiple) and print its stats
for author in $author_name
	print_stats $author
end
